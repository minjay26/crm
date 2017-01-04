'use strict'

angular.module('app')

    .constant('optionUrl', {

        logoutUrl: '/logout',

        mails: "mails",
        mail: "mails/{id}",
        upload: "mails/upload",
        mailSubmit: "mails/reply",
        mailRead:"mails/read"
    });

