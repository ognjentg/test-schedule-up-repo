var facultyView = {
    panel: {
        id: "facultyPanel",
        adjust: true,
        rows: [{
            view: "toolbar",
            padding: 8,
            css: "panelToolbar",
            cols: [{
                view: "label",
                width: 400,
                template: "<span class='fa fa-book'></span> Faculties"
            }, {}, {
                id: "addFacultyBtn",
                view: "button",
                type: "iconButton",
                label: "Add faculty",
                icon: "plus-circle",
                click: 'facultyView.showAddDialog',
                autowidth: true
            }]
        }, {
            view: "datatable",
            css: "webixDatatable",
            multiselect: false,
            id: "facultyDT",
            resizeColumn: true,
            resizeRow: true,
            onContext: {},
            columns: [{
                id: "id",
                hidden: true,
                fillspace: true,
                editor: "text",
                sort: "string",
                header: [
                    "ID", {
                        content: "textFilter"
                    }
                ]
            }, {
                id: "name",
                fillspace: true,
                editor: "text",
                sort: "string",
                header: [
                    "Name", {
                        content: "textFilter"
                    }
                ]
            }, {
                id: "address",
                fillspace: true,
                editor: "text",
                sort: "string",
                header: [
                    "Address", {
                        content: "textFilter"
                    }
                ]
            }
            ],
            select: "row",
            navigation: true,
            editable: true,
            editaction: "dblclick",
            url: "faculty",
            on: {
                onAfterContextMenu: function (item) {
                    this.select(item.row);
                }
            }
        }]
    },

    selectPanel: function () {
        $$("main").removeView(rightPanel);
        rightPanel = "facultyPanel";

        var panelCopy = webix.copy(this.panel);

        $$("main").addView(webix.copy(panelCopy));

        connection.attachAjaxEvents("facultyDT", "faculty");

        webix.ui({
            view: "contextmenu",
            id: "facultyContextMenu",
            width: 200,
            data: [{
                id: "1",
                value: "Edit",
                icon: "pencil-square-o"
            }, {
                $template: "Separator"
            }, {
                id: "2",
                value: "Delete",
                icon: "trash"
            }],
            master: $$("facultyDT"),
            on: {
                onItemClick: function (id) {
                    var context = this.getContext();
                    switch (id) {
                        case "1":
                            facultyView.showChangeFacultyDialog($$("facultyDT").getItem(context.id.row));
                            break;
                        case "2":
                            var delBox = (webix.copy(commonViews.deleteConfirm("faculty")));
                            delBox.callback = function (result) {
                                if (result == 1) {
                                    $$("facultyDT").remove(context.id.row);
                                    util.messages.showMessage("Faculty deleted successfully.");
                                }
                            }
                            webix.confirm(delBox);
                            break;
                    }
                }
            }
        });
    },

    addDialog: {
        view: "popup",
        id: "addFacultyDialog",
        modal: true,
        position: "center",
        body: {
            id: "addFacultyInside",
            rows: [{
                view: "toolbar",
                cols: [{
                    view: "label",
                    label: "<span class='webix_icon fa-book'></span> Add faculty",
                    width: 400
                }, {}, {
                    hotkey: 'esc',
                    view: "icon",
                    icon: "close",
                    align: "right",
                    click: "util.dismissDialog('addFacultyDialog');"
                }]
            }, {
                view: "form",
                id: "addFacultyForm",
                width: 500,
                elementsConfig: {
                    labelWidth: 140,
                    bottomPadding: 18
                },
                elements: [{
                    view: "text",
                    id: "name",
                    name: "name",
                    label: "Name:",
                    invalidMessage: "Enter faculty name!",
                    required: true
                }, {
                    view: "checkbox",
                    name: "validFacultyName",
                    hidden: true,
                    value: 1
                }, {
                    view: "text",
                    id: "address",
                    name: "address",
                    label: "Address:",
                    invalidMessage: "Enter address!",
                    required: true
                }, {
                    margin: 5,
                    cols: [{}, {
                        id: "saveFaculty",
                        view: "button",
                        value: "Save",
                        type: "form",
                        click: "facultyView.save",
                        width: 150
                    }]
                }],
                rules: {
                    "name": function (value) {
                        return value;
                    }
                }
            }]
        }
    },

    showAddDialog: function () {
        webix.ui(webix.copy(facultyView.addDialog)).show();
        webix.UIManager.setFocus("name");
    },

    save: function () {
        var form = $$("addFacultyForm");
        if (form.validate()) {

            form.elements.validFacultyName.setValue(1);

            if (form.validate()) {
                var newItem = {
                    name: form.getValues().name,
                    address: form.getValues().address
                };
                $$("facultyDT").add(newItem);
                util.dismissDialog('addFacultyDialog');
            }
        }
    },

    changeFacultyDialog: {
        view: "popup",
        id: "changeFacultyDialog",
        modal: true,
        position: "center",
        body: {
            id: "changeFacultyInside",
            rows: [{
                view: "toolbar",
                cols: [{
                    view: "label",
                    label: "<span class='webix_icon fa-book'></span> Change faculty data",
                    width: 400
                }, {}, {
                    view: "icon",
                    icon: "close",
                    align: "right",
                    click: "util.dismissDialog('changeFacultyDialog');"
                }]
            }, {
                view: "form",
                id: "changeFacultyForm",
                width: 500,
                elementsConfig: {
                    labelWidth: 140,
                    bottomPadding: 18
                },
                elements: [{
                    view: "text",
                    name: "id",
                    hidden: true
                }, {
                    view: "text",
                    id: "name",
                    name: "name",
                    label: "Name:",
                    invalidMessage: "Enter faculty name!",
                    required: true
                }, {
                    view: "text",
                    id: "address",
                    name: "address",
                    label: "Address:",
                    required: true
                }, {
                    margin: 5,
                    cols: [{}, {
                        id: "saveChangedFaculty",
                        view: "button",
                        value: "Save",
                        type: "form",
                        click: "facultyView.saveChangedFaculty",
                        hotkey: "enter",
                        width: 150
                    }]
                }],
                rules: {
                    "name": function (value) {
                        if (!value)
                            return false;
                        return true;
                    }
                }
            }]
        }
    },

    showChangeFacultyDialog: function (faculty) {
        webix.ui(webix.copy(facultyView.changeFacultyDialog));
        var form = $$("changeFacultyForm");
        form.elements.id.setValue(faculty.id);
        form.elements.name.setValue(faculty.name);
        form.elements.address.setValue(faculty.address);

        setTimeout(function () {
            $$("changeFacultyDialog").show();
            webix.UIManager.setFocus("name");
        }, 0);
    },

    saveChangedFaculty: function () {
        var form = $$("changeFacultyForm");
        if (form.validate()) {
            var newItem = {
                id: form.getValues().id,
                name: form.getValues().name,
                address: form.getValues().address
            };

            connection.sendAjax("PUT", "faculty",
                function (text, data, xhr) {
                    if (text) {
                        util.messages.showMessage("Data successfully changed.");
                        $$("facultyDT").updateItem(newItem.id, newItem);
                    } else
                        util.messages.showErrorMessage("Data not successfully changed.");
                }, function () {
                    util.messages.showErrorMessage("Data not successfully changed.");
                }, newItem);

            util.dismissDialog('changeFacultyDialog');
        }
    }
};