/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')

    .controller('RecordsController', function ($scope, $rootScope, FileUploader, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        $scope.currentPage = 1;
        getWorkingRecord();
        function getWorkingRecord() {
            $http({
                method: 'get',
                url: optionUrl.workingRecords,
                params: {
                    page: $scope.currentPage - 1,
                },
            }).success(function (data) {
                $scope.totalItems = data.totalElements;
                $scope.items = data.content;
                $scope.loadDivVisible = false;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }

        $scope.pageChanged = function () {
            getWorkingRecord();
        };
    })


