var AWS = require("aws-sdk");
var convert = require('xml-js');
exports.handler = (event, context, callback) => {
    var s3 = new AWS.S3();
    var sourceBucket = "i2i.input";
    var destinationBucket = "i2i.output";
    var objectKey = event.Records[0].s3.object.key;

    s3.getObject({
        Bucket: sourceBucket,
        Key: objectKey
    }, function(err, data) {
        if (err) {
            console.log(err, err.stack);
            callback(err);
        } else {
            var dataBody = data.Body.toString('ascii');
            var options = {ignoreComment: true, alwaysChildren: true};
            var jsonFile = convert.xml2json(dataBody, options);
            var params = {Bucket: destinationBucket, Key: objectKey.slice(0, -4) + ".json", Body: jsonFile};
            s3.upload(params, function(err, data) {
            console.log(err, data);
            });
        }
    });

};