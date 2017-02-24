/**
 * Created by Administrator on 2017/1/13.
 */
'user strict'

angular.module('app')

    .controller('RecordsController', function ($scope, $rootScope, FileUploader, $location, $http, $routeParams, $window, $cookies, optionUrl) {
        $scope.currentPage = 1;
        $scope.registerTypes = {
            availableOptions: [
                {value: '1', name: '全部'},
                {value: '2', name: '上班登记'},
                {value: '3', name: '下班登记'},
            ],
            selectedOption: {value: '1', name: '全部'}
        };

        $scope.dt = {
            startDate: null,
            endDate: null
        }
        getWorkingRecord();
        function getWorkingRecord() {
            if ($scope.registerTypes.selectedOption.value == 1) {
                $scope.registerTypes.selectedOption.name = "";
            }
            $http({
                method: 'get',
                url: optionUrl.workingRecords,
                params: {
                    page: $scope.currentPage - 1,
                    startDate: $scope.dt.startDate,
                    endDate: $scope.dt.endDate,
                    registerType: $scope.registerTypes.selectedOption.name
                },
            }).success(function (data) {
                $scope.totalItems = data.totalElements;
                $scope.items = data.content;
                $scope.loadDivVisible = false;
            }).error(function () {
                $scope.openAlert('danger', '网络异常，请稍后再试或者联系管理员', 5000);
            });
        }


        $scope.changeType = function () {
            getWorkingRecord();
        }

        $scope.search = function () {
            getWorkingRecord();
        }

        $scope.pageChanged = function () {

        };
    })


