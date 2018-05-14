var subjectView = {
    panel: {
        id: "subjectPanel",
        adjust: true,
        rows: [{
            view: "toolbar",
            padding: 8,
            css: "panelToolbar",
            cols: [{
                view: "label",
                width: 400,
                template: "<span class='fa fa-book'></span> Subjects"
            }, {}, {
                id: "addSubjectBtn",
                view: "button",
                type: "iconButton",
                label: "Add subject",
                icon: "plus-circle",
                click: 'subjectView.showAddDialog',
                autowidth: true
            }]
        }, {
            view: "datatable",
            css: "webixDatatable",
            multiselect: false,
            id: "subjectDT",
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
                id: "ects",
                fillspace: true,
                editor: "text",
                sort: "int",
                header: [
                    "ECTS", {
                        content: "numberFilter"
                    }
                ]
            }
            ],
            select: "row",
            navigation: true,
            editable: true,
            editaction: "dblclick",
            url: "subject",
            on: {
                onAfterContextMenu: function (item) {
                    this.select(item.row);
                }
            }
        }]
    },

    selectPanel: function () {
        $$("main").removeView(rightPanel);
        rightPanel = "subjectPanel";

        var panelCopy = webix.copy(this.panel);

        $$("main").addView(webix.copy(panelCopy));

        connection.attachAjaxEvents("subjectDT", "subject");

        webix.ui({
            view: "contextmenu",
            id: "subjectContextMenu",
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
            master: $$("subjectDT"),
            on: {
                onItemClick: function (id) {
                    var context = this.getContext();
                    switch (id) {
                        case "1":
                            subjectView.showChangeSubjectDialog($$("subjectDT").getItem(context.id.row));
                            break;
                        case "2":
                            var delBox = (webix.copy(commonViews.deleteConfirm("subject")));
                            delBox.callback = function (result) {
                                if (result == 1) {
                                    $$("subjectDT").remove(context.id.row);
                                    util.messages.showMessage("Subject deleted successfully.");
                                }
                            };
                            webix.confirm(delBox);
                            break;
                    }
                }
            }
        });
    },

    addDialog: {
        view: "popup",
        id: "addSubjectDialog",
        modal: true,
        position: "center",
        body: {
            id: "addSubjectInside",
            rows: [{
                view: "toolbar",
                cols: [{
                    view: "label",
                    label: "<span class='webix_icon fa-book'></span> Add subject",
                    width: 400
                }, {}, {
                    hotkey: 'esc',
                    view: "icon",
                    icon: "close",
                    align: "right",
                    click: "util.dismissDialog('addSubjectDialog');"
                }]
            }, {
                view: "form",
                id: "addSubjectForm",
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
                    invalidMessage: "Enter subject name!",
                    required: true
                }, {
                    view: "checkbox",
                    name: "validSubjectName",
                    hidden: true,
                    value: 1
                }, {
                    view: "text",
                    id: "ects",
                    name: "ects",
                    label: "ECTS:",
                    required: true
                }, {
                    margin: 5,
                    cols: [{}, {
                        id: "saveSubject",
                        view: "button",
                        value: "Save",
                        type: "form",
                        click: "subjectView.save",
                        hotkey: "enter",
                        width: 150
                    }]
                }],
                rules: {
                    "name": function (value) {
                        if (!value)
                            return false;
                        if (value.length > 20) {
                            $$('addSubjectForm').elements.name.config.invalidMessage = 'Up to 20 characters allowed';
                            return false;
                        }
                        return true;
                    },
                    "ects": function (value) {
                        if (!value) {
                            $$('addSubjectForm').elements.ects.config.invalidMessage = 'Enter ECTS!';
                            return false;
                        }
                        else if (isNaN(value)) {
                            $$('addSubjectForm').elements.ects.config.invalidMessage = 'ECTS must be a number';
                            return false;
                        }
                        return true;
                    }
                }
            }]
        }
    },

    showAddDialog: function () {
        webix.ui(webix.copy(subjectView.addDialog)).show();
        webix.UIManager.setFocus("name");
    },

    save: function () {
        var form = $$("addSubjectForm");
        if (form.validate()) {

            form.elements.validSubjectName.setValue(1);

            if ($$("addSubjectForm").validate()) {
                var newItem = {
                    name: form.getValues().name,
                    ects: form.getValues().ects
                };
                $$("subjectDT").add(newItem);
                util.dismissDialog('addSubjectDialog');
            }
        }
    },

    changeSubjectDialog: {
        view: "popup",
        id: "changeSubjectDialog",
        modal: true,
        position: "center",
        body: {
            id: "changeSubjectInside",
            rows: [{
                view: "toolbar",
                cols: [{
                    view: "label",
                    label: "<span class='webix_icon fa-book'></span> Change subject data",
                    width: 400
                }, {}, {
                    view: "icon",
                    icon: "close",
                    align: "right",
                    click: "util.dismissDialog('changeSubjectDialog');"
                }]
            }, {
                view: "form",
                id: "changeSubjectForm",
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
                    invalidMessage: "Enter subject name!",
                    required: true
                }, {
                    view: "text",
                    id: "ects",
                    name: "ects",
                    label: "ECTS:",
                    required: true
                }, {
                    margin: 5,
                    cols: [{}, {
                        id: "saveChangedSubject",
                        view: "button",
                        value: "Save",
                        type: "form",
                        click: "subjectView.saveChangedSubject",
                        hotkey: "enter",
                        width: 150
                    }]
                }],
                rules: {
                    "name": function (value) {
                        if (!value)
                            return false;
                        return true;
                    },
                    "ects": function (value) {
                        if (!value) {
                            $$('addSubjectForm').elements.ects.config.invalidMessage = 'Enter ECTS!';
                            return false;
                        }
                        else if (isNaN(value)) {
                            $$('addSubjectForm').elements.ects.config.invalidMessage = 'ECTS must be a number';
                            return false;
                        }
                        return true;
                    }
                }
            }]
        }
    },

    showChangeSubjectDialog: function (subject) {
        webix.ui(webix.copy(subjectView.changeSubjectDialog));
        var form = $$("changeSubjectForm");
        form.elements.id.setValue(subject.id);
        form.elements.name.setValue(subject.name);
        form.elements.ects.setValue(subject.ects);

        setTimeout(function () {
            $$("changeSubjectDialog").show();
            webix.UIManager.setFocus("name");
        }, 0);
    },

    saveChangedSubject: function () {
        if ($$("changeSubjectForm").validate()) {
            var newItem = {
                id: $$("changeSubjectForm").getValues().id,
                name: $$("changeSubjectForm").getValues().name,
                ects: $$("changeSubjectForm").getValues().ects
            };

            connection.sendAjax("PUT", "subject",
                function (text, data, xhr) {
                    if (text) {
                        util.messages.showMessage("Data successfully changed.");
                        $$("subjectDT").updateItem(newItem.id, newItem);
                    } else
                        util.messages.showErrorMessage("Data not successfully changed.");
                }, function () {
                    util.messages.showErrorMessage("Data not successfully changed.");
                }, newItem);

            util.dismissDialog('changeSubjectDialog');
        }
    }
};