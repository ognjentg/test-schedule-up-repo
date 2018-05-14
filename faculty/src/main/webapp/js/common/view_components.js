/**
 *
 */

var commonViews = {
    deleteConfirm: function (titleEntity, textEntity) {
        var text = titleEntity;
        if (textEntity) text = textEntity;
        return {
            title: "Deleting " + titleEntity,
            ok: "Yes",
            cancel: "No",
            width: 500,
            text: "Are you sure you want to delete " + text + "?"
        };
    },


    confirm: function (titleEntity, textEntity) {
        var text = titleEntity;
        if (textEntity) text = textEntity;
        return {
            title: titleEntity,
            ok: "Yes",
            cancel: "No",
            width: 500,
            text: text
        };
    },

    //if called with webix.alert(), and did not used cancelButtonEntity, there will be an okButtonEntity only
    confirmOkCancel: function (titleEntity, textEntity, okButtonEntity, cancelButtonEntity) {
        return {
            view: "popup",
            position: "center",
            title: titleEntity,
            ok: okButtonEntity,
            cancel: cancelButtonEntity,
            width: 500,
            text: textEntity
        }
    }

}