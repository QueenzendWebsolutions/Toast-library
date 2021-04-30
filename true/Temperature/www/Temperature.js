// var exec = require('cordova/exec');

// exports.coolMethod = function (arg0, success, error) {
//     exec(success, error, 'Temperature', 'coolMethod', [arg0]);
// };
var exec = require('cordova/exec');

exports.checkTemperature = function(success, error) {
    exec(success, error, 'Temperature', 'checkTemperature');
};

exports.isDeviceCompatible = function(success, error) {
    exec(success, error, 'Temperature', 'isDeviceCompatible');
};