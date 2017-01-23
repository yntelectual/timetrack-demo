angular.module('app', ['ngRoute', 'ngResource', 'angular-growl', 'ngAnimate']);
angular.module('app').config(function ($routeProvider, $locationProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "partials/list.html"
            })
            .when("/add-entry", {
                templateUrl: "partials/add.html"
            })
            .otherwise({
                redirectTo: '/'
            });
//    $locationProvider.html5Mode(true);
});

angular.module('app').config(['growlProvider', function (growlProvider) {
        growlProvider.globalTimeToLive(5000);
        growlProvider.globalPosition('top-center');
    }]);

angular.module('app').controller('AppCtrl', function ($scope, $location) {
    $scope.val = "hello";
    $scope.isActive = function (route) {
        console.log('verify path ' + route);
        return route === $location.path();
    }
});