var express = require('express'),
    router = express.Router();

var AuthCtrl = require('../authentication/authentication.server.controller'),
    HomeCtrl = require('./home.server.controller');

router.get("/login", HomeCtrl.login);
router.post("/login", HomeCtrl.loginSubmit);
router.get("/logout", AuthCtrl.IsAuth, HomeCtrl.logout);

router.get('/', AuthCtrl.IsAuth, function (req, res, next) {
    res.render('home');
});

router.all('*', AuthCtrl.IsAuth);

router.get('/home/inituser', HomeCtrl.initUser);
router.get('/home/inituserapps',HomeCtrl.initUserApps);
//
//router.get('/home/inituserauthority',HomeCtrl.initUserAuthority);
//router.get('/home/initmenus', HomeCtrl.initMenus);

router.get("/home/getsettings", HomeCtrl.getSettings);



module.exports = router;
