var express = require('express'),
    router = express.Router();
var AuthCtrl = require('../authentication/authentication.server.controller');

router.get('/', AuthCtrl.AuthRedirect, function (req, res, next) {

    res.render('home', {title: 'Express'});
});
router.get('/ocload', function (req, res, next) {

    res.render('ocload', {title: 'Express'});
});

router.get('/erpview', AuthCtrl.AuthRedirect, function (req, res, next) {

    res.render('erpview', {title: 'Express'});
});
router.get('/home2', function (req, res, next) {

    res.render('home2');
});


module.exports = router;
