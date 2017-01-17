/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')

    .controller('GoOutController', function ($scope, $rootScope, FileUploader, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        var now = new Date();
        $scope.date = now.getFullYear() + '-' + now.getMonth() + 1 + '-' + now.getDate();
        $scope.currentPage = 1;
        $scope.registerHided = true;
        $scope.recordHided = true;
        getApprovers();
        function getApprovers() {
            $http({
                method: 'get',
                url: optionUrl.approvers,
            }).success(function (data) {
                $scope.approvers = data;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        $scope.goOutSwitch = function (index) {
            switch (index) {
                case 1:
                    $scope.registerHided = false;
                    $scope.recordHided = true;
                    return;
                case 2:
                    $scope.recordHided = false;
                    $scope.registerHided = true;
                    getGoOutRecords();
                    return;
            }
        }

        $scope.apply = function () {
            var reason = $scope.reason,
                approver = $scope.approvers.selectedOption,
                date1 = new Date(),
                date2 = new Date();
            date1.setHours($scope.startDate.getHours(), $scope.startDate.getMinutes());
            var start = date1;
            date2.setHours($scope.endDate.getHours(), $scope.endDate.getMinutes());
            var end = date2;

            $http({
                method: 'post',
                url: optionUrl.apply,
                data: {
                    reason: reason,
                    approver: approver,
                    startDate: start,
                    endDate: end
                },
            }).success(function (data) {
                $scope.recordHided = false;
                $scope.registerHided = true;
                // getGoOutRecords();
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        function getGoOutRecords() {
            $http({
                method: 'get',
                url: optionUrl.goOutRecords,
                params: {
                    page: $scope.currentPage - 1,
                },
            }).success(function (data) {
                $scope.totalItems = data.totalElements;
                $scope.items = data.content;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        $scope.pageChanged = function () {
            getGoOutRecords();
        };
    })


