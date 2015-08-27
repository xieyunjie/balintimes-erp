/**
 * Created by AlexXie on 2015/8/10.
 */
var AuthServerController = {};
module.exports = AuthServerController;


AuthServerController.IsAuth = function (req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }

    res.redirect('/login');
};

AuthServerController.AuthRedirect = function (req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }

    res.redirect('/login');
};

AuthServerController.AuthAjax = function (req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }

    //res.redirect('/login');
    var NotAuth = {
        success: false,
        status: 40001,
        path: req.path
    };

    res.send(NotAuth);
};


AuthServerController.MidShow = function (req, res, next) {
    console.info("MidShow MidShow MidShow");
    next();
};