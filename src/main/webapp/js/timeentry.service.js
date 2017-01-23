angular.module('app')
        .factory('TimeEntryService', function ($resource) {
            var resource = $resource('rest/v1/timeentry/:id', {}, {});
            return resource;
        });