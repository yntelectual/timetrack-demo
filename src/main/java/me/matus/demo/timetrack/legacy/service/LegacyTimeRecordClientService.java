package me.matus.demo.timetrack.legacy.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import me.matus.demo.timetrack.frontend.cdi.TracedExecution;
import me.matus.demo.timetrack.frontend.exception.ServiceException;
import me.matus.demo.timetrack.frontend.model.TimeRecordDTO;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Client service for legacy time record api.
 *
 * @author Matus_Majchrak
 */
@Stateless
@TracedExecution
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LegacyTimeRecordClientService {
    
    private static final Logger LOG = LoggerFactory.getLogger(LegacyTimeRecordClientService.class);
    private static final DateFormat sdf = new SimpleDateFormat("DD.MM.yyyy HH:mm:ss");
    
    public List<TimeRecordDTO> fetchRecordsFromLegacySystem(String email, Long offset, Long length) throws ServiceException {
        try {
            List<TimeRecordDTO> legacyData = getLegacyClient().fetchRecords(email, offset, length);
            return stripNulls(legacyData);
        } catch (Exception e) {
            throw new ServiceException("CANNOT_FETCH_LEGACY_DATA", e);
        }
    }
    
    public TimeRecordDTO createNew(TimeRecordDTO entry) throws ServiceException {
        try {
            entry = getLegacyClient().createRecord(entry.getEmail(), entry.getStart() != null ? sdf.format(entry.getStart()) : null, entry.getEnd() != null ? sdf.format(entry.getEnd()) : null);
            return entry;
        } catch (Exception e) {
            throw new ServiceException("CANNOT_CREATE_LEGACY_DATA", e);
        }
    }
    
    private LegacyTimeRecordApi getLegacyClient() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        String legacyURL = System.getenv("LEGACY_SYSTEM_URL");
        LOG.info("Trying to fetch data from {}", legacyURL);
        ResteasyWebTarget target = client.target(legacyURL);
        LegacyTimeRecordApi restApiClient = target.proxy(LegacyTimeRecordApi.class);
        return restApiClient;
    }
    
    private List<TimeRecordDTO> stripNulls(List<TimeRecordDTO> legacyData) {
        List<TimeRecordDTO> results = new ArrayList<>();
        for (TimeRecordDTO timeRecordDTO : legacyData) {
            if (timeRecordDTO != null) {
                results.add(timeRecordDTO);
            }
        }
        return results;
    }
}
