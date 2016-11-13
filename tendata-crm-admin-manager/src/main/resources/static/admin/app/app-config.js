'use strict'

angular.module('app')

    .constant('optionUrl', {

        logoutUrl: '/logout',
        upToken: 'uptoken',
        categoryTab: 'customer-service-catalog',
        customerService: 'customer_service',
        deleteCustomerService: 'customer_service/delete/{id}',
        customerServiceAnswer: 'customer_service/answer/{id}',
        customerServiceView: 'customer_service/view/{id}',
        customerServiceDownload: 'customer_service/download',
        customerServiceAttachment: 'customer_service_attachment',
        deleteAttachment:'customer_service_attachment/delete',
    });

