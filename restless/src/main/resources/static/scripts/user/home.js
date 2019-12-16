function logout() {
    localStorage.clear();
    window.location.replace(ROOT_PATH + "/index.html")
}

function get_books() {
    $.ajax({
        url : ROOT_PATH + '/books',
        type: 'Get',
        success: function(data) {
            populateBooks(data);
        },
        error: function(xhr) {
            alert(xhr.responseText);
        }
    });
}

function get_book(id) {
    $.ajax({
        url: ROOT_PATH + '/books/' + id,
        type: 'Get',
        success: function(data) {
            console.log(data);
        },
        error: function(xhr) {
            alert(xhr.responseText);
        }
    });

}

function delete_book(id) {
    $.ajax({
        url: ROOT_PATH + '/books/' + id,
        type: 'Delete',
        success: function(data) {
            console.log(data);
        },
        error: function(xhr) {
            alert(xhr.responseText);
        }
    });
}

function populateBooks(books) {
    let tableBody = document.getElementById("books-table").getElementsByTagName("tbody")[0];
    tableBody.innerHTML = "";
    books.forEach((book, index) => {
        let row = tableBody.insertRow();
        let cellId = row.insertCell(0);
        let cellTitle = row.insertCell(1);
        let cellIsbn = row.insertCell(2);
        let cellActions = row.insertCell(3);

        let viewBtn = createButtonAction("viewBtn" ,book.id, "btn-primary", "View");
        viewBtn.addEventListener("click", view_book);
        let deleteBtn = createButtonAction("deleteBtn" ,book.id, "btn-danger", "Delete");
        deleteBtn.addEventListener("click", remove_book);

        cellId.outerHTML = '<th scope="row">' + (index+1) + '</th>';
        cellTitle.innerHTML = book.title;
        cellIsbn.innerHTML = book.isbn;
        cellActions.appendChild(viewBtn);
        cellActions.appendChild(deleteBtn);
    });

}

function createButtonAction(name, rowId, className, value) {
    let btn = document.createElement("Button");
    btn.setAttribute("name", name);
    btn.setAttribute("id", rowId);
    btn.setAttribute("type", "button");
    btn.setAttribute("class", "btn " + className);
    btn.innerHTML = value;
    return btn;
}


function view_book(event) {
    get_book(event.target.id);

}

function remove_book(event) {
    delete_book(event.target.id);
}