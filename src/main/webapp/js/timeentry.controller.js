angular.module('app').controller('TimeEntryCtrl', TimeEntryCtrl);
TimeEntryCtrl.$inject = ['$scope', 'TimeEntryService', 'growl'];
function TimeEntryCtrl($scope, TimeEntryService, growl) {
    var vm = this;
    vm.title = 'Time entries';
    vm.offset = 0;
    vm.length = 10;
    vm.entries = [];
    vm.email = null;
    vm.entry = {};


    vm.fetchData = function () {
        TimeEntryService.query({offset: vm.offset, length: vm.length, email: vm.email}, function (data) {
            console.log('got data');
            growl.success("<b>Success</b> Fetched data from legacy service", {});
            vm.entries = data;
        }, function () {
            growl.error("<b>Darn</b> Something went wrong during record fetching", {});
        });
    };

    vm.create = function () {
        TimeEntryService.save(vm.entry, function () {
            growl.success("<b>Success</b> New time record has been stored", {});
        }, function () {
            growl.error("<b>Darn</b> Something went wrong during record save", {});
        });
    };

    vm.next = function () {
        vm.offset += 10;
        vm.fetchData();
    };

    vm.prev = function () {
        vm.offset -= 10;
        vm.fetchData();
    };

    //init;
    vm.fetchData();

}