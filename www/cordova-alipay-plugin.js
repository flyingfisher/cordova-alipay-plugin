var exec = require('cordova/exec');

exports.pay = function(orderStr, appScheme, callback) {
    exec(success, error, "AlipayPlugin", "pay", [orderStr, appScheme]);

    function success(rst){
        if(callback)
            callback(null, rst);
    }

    function error(err){
        if(callback)
            callback(err);
    }
}

exports.isWalletExist = function(callback) {
    exec(success, error, "AlipayPlugin", "isWalletExist", []);

    function success(rst){
        if(callback)
            callback(null, rst);
    }

    function error(err){
        if(callback)
            callback(err);
    }
}