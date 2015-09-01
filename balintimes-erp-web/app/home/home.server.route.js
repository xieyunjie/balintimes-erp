var express = require('express'),
    router = express.Router();

var AuthCtrl = require('../authentication/authentication.server.controller'),
    HomeCtrl = require('./home.server.controller');


router.get("/login",HomeCtrl.login);
router.post("/login",HomeCtrl.loginSubmit);
router.get("/logout",HomeCtrl.logout);

router.get('/', AuthCtrl.IsAuth, function (req, res, next) {
    res.render('home');
});

router.all('*',AuthCtrl.IsAuth);

router.get('/home/inituser',HomeCtrl.initUser);
router.get('/home/initmenus',HomeCtrl.initMenus);

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
