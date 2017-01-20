'use strict'

angular.module('app')

    .constant('optionUrl', {

        logoutUrl: '/logout',

        approvers: "users/approvers",

        mails: "mails",
        mail: "mails/{id}",
        upload: "mails/upload",
        mailSubmit: "mails/reply",
        mailRead: "mails/read",

        regulations: "attendance/regulations",
        register: "attendance/register",
        workingRecords: "attendance/working_records",
        goOutRecords: "attendance/goOutRecords",
        apply:"attendance/apply",

        getUsers:"users"
    });

