function PersonTable(container) {

    /**
     * Reference to the root object.
     *
     * @type {PersonTable}
     */
    var root = this;

    // Create table elements
    this.container = container;

    this.tableElement = document.createElement("table");
    container.append(this.tableElement);

    this.tableHeadElement = document.createElement("thead");
    this.tableElement.appendChild(this.tableHeadElement);

    this.tableBodyElement = document.createElement("tbody");
    this.tableElement.appendChild(this.tableBodyElement);


    this.init = function () {
        root.createHeaders();
        root.container.append(root.createRefreshButton());
        root.container.append(root.createAddButton());
        root.populate();
    }

    this.createHeaders = function () {
        var tableHeaders = ["ID", "First name", "Last name", "Phone number", "Delete", "Update"];
        tableHeaders.forEach(function (name) {
            var th = document.createElement("th");
            th.innerText = name;
            root.tableHeadElement.appendChild(th);
        });
    };

    /**
     * Creates, inserts and returns a button that refreshes the data in the table.
     */
    this.createRefreshButton = function () {
        var button = document.createElement("button");
        button.innerText = "REFRESH";
        button.addEventListener("click", function () {
            root.clear();
            root.populate();
        });

        return button;
    };

    this.appendPerson = function (person) {

        var tr = document.createElement("tr");
        Object.values(person).forEach(function (v, i) {
            var td = document.createElement("td");
            if (i != 0)
                td.contentEditable = true;
            td.innerText = v;
            tr.appendChild(td);
        });

        // Append actions
        var deleteTd = document.createElement("td");
        var deleteButton = document.createElement("button");
        deleteButton.innerText = "DELETE";
        deleteButton.addEventListener('click', function () {
            deletePerson(person['id'], function (response) {
                if (response.status == 204) {
                    tr.parentNode.removeChild(tr);
                } else {
                    alert("error on delete");
                }
            });
        });

        deleteTd.appendChild(deleteButton);
        tr.appendChild(deleteTd);

        var editTd = document.createElement("td");
        var editButton = document.createElement("button");
        editButton.innerText = "UPDATE";
        editButton.addEventListener("click", function () {

            person["firstName"] = tr.childNodes[1].innerText;
            person["lastName"] = tr.childNodes[2].innerText;
            person["phone"] = tr.childNodes[3].innerText;

            updatePerson(person, function (response) {
                if (response.status !== 200) {
                    alert("error on update");
                }
            })
        });

        editTd.append(editButton);
        tr.appendChild(editTd);

        // Fix: should use root.tableElement
        document.getElementsByTagName("tbody")[0].appendChild(tr);
    };

    this.createAddButton = function () {


        root.container.innerHTML += '<div id="table-add-container" style="display:none">' +
            '    <form id="table-add-table">' +
            '        <input type="text" name="firstName" placeholder="First name" id="table-add-input-firstName">' +
            '        <input type="text" name="lastName" placeholder="Last name" id="table-add-input-lastName">' +
            '        <input type="text" name="phone" placeholder="Phone" id="table-add-input-phone">' +
            '        <input type="submit" value="Create" id="table-add-button">' +
            '    </form>' +
            '</div>';

        var button = document.createElement("button");
        button.innerText = "NEW";
        button.addEventListener("click", function () {
            document.getElementById("table-add-container").style.display = 'block';
        });

        var createButton = document.getElementById("table-add-button");
        createButton.addEventListener("click", function (e) {
            e.preventDefault();

            var firstName = document.getElementById("table-add-input-firstName").value;
            var lastName = document.getElementById("table-add-input-lastName").value;
            var phone = document.getElementById("table-add-input-phone").value;

            createPerson(firstName, lastName, phone, function (created) {
                console.log(created);
                root.appendPerson(created);
                document.getElementById("table-add-container").style.display = 'none';
            });
        });

        return button;
    };

    /**
     * Clears the rows in the table.
     */
    this.clear = function () {
        root.tableBodyElement.innerHTML = '';
    };

    /**
     * Populates the table with all the persons in the api.
     */
    this.populate = function () {
        getAllPersons(function (persons) {
            persons.forEach(function (person) {
                root.appendPerson(person);
            });
        });
    }
}


function getPerson(id, callback) {
    fetch("api/person/" + id).then(function (data) {
        return data.json();
    }).then(function (person) {
        callback(person);
    });
}

function getAllPersons(callback) {
    fetch("api/person")
        .then(function (data) {
            return data.json();
        }).then(function (persons) {
        callback(persons);
    });
}

function deletePerson(id, callback) {
    fetch("api/person/" + id, {method: 'delete'}).then(function (response) {
        callback(response);
    });
}

function updatePerson(person, callback) {
    fetch("api/person/" + person['id'], {
        method: 'put',
        body: JSON.stringify(person),
        headers: {'Content-Type': 'application/json'}
    }).then(function (response) {
        callback(response);
    });
}

function createPerson(firstName, lastName, phone, callback) {
    fetch("api/person", {
        method: 'post',
        body: JSON.stringify({firstName: firstName, lastName: lastName, phone: phone}),
        headers: {'Content-Type': 'application/json'}
    }).then(function (response) {
        return response.json();
    }).then(function (person) {
        callback(person);
    });
}