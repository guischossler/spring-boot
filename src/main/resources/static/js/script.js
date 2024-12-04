const persons = [];

const addElementHtml = (
  tagHtml,
  attributeHtml = null,
  attributeNameHtml = null
) => {
  const element = document.createElement(tagHtml);

  if (attributeHtml && attributeNameHtml) {
    element.setAttribute(attributeHtml, attributeNameHtml);
  }

  document.body.appendChild(element);
  return element;
};

const fetchData = () => {
  fetch("http://localhost:8080/api/persons")
    .then((res) => {
      if (!res.ok) {
        throw new Error("Network response was not ok " + res.statusText);
      }

      return res.json();
    })
    .then((data) => {
      persons.length = 0; // Limpa o array antes de adicionar os novos dados
      persons.push(...data);

      if (persons.length == 0) {
        emptyPersonArrayToDisplay();
        return;
      }

      console.log(persons);

      displayPersons();
    })
    .catch((error) => {
      console.error("Fetch error:", error);
    });
};

const createTable = (headers) => {
  const main = document.querySelector(".main");
  const table = document.createElement("table");
  const thead = document.createElement("thead");
  const tbody = document.createElement("tbody");

  const headerRow = document.createElement("tr");
  headers.forEach((header) => {
    const th = document.createElement("th");
    th.textContent = header;
    /* linha abaixo seta o proprio nome da coluna
     como o id dele, remove acentuacao */
    th.setAttribute(
      "id",
      "th" + header.normalize("NFD").replace(/[\u0300-\u036f\-]/g, "")
    );

    headerRow.appendChild(th);
  });
  thead.appendChild(headerRow);

  table.appendChild(thead);
  table.appendChild(tbody);
  main.appendChild(table);

  return table;
};

const emptyPersonArrayToDisplay = () => {
  const main = document.querySelector(".main");
  main.textContent = ""; // Limpa o conteúdo anterior

  const table = createTable(["Id", "Nome", "E-mail"]);
  const tbody = table.querySelector("tbody");

  const row = document.createElement("tr");

  const idCell = document.createElement("td");
  idCell.textContent = "N/A";
  idCell.setAttribute("class", "tdId");
  row.appendChild(idCell);

  const nameCell = document.createElement("td");
  nameCell.textContent = "N/A";
  nameCell.setAttribute("class", "tdName");
  row.appendChild(nameCell);

  const emailCell = document.createElement("td");
  emailCell.textContent = "N/A";
  emailCell.setAttribute("class", "tdEmail");
  row.appendChild(emailCell);

  tbody.appendChild(row);
};

// Exibe os dados na tabela
const displayPersons = () => {
  const main = document.querySelector(".main");
  main.textContent = ""; // Limpa o conteúdo anterior

  const table = createTable(["Id", "Nome", "E-mail", "Ação"]);
  const tbody = table.querySelector("tbody");

  persons.forEach((person) => {
    const row = document.createElement("tr");

    const idCell = document.createElement("td");
    idCell.textContent = person.id;
    idCell.setAttribute("class", "tdId");
    row.appendChild(idCell);

    const nameCell = document.createElement("td");
    nameCell.textContent = person.name;
    nameCell.setAttribute("class", "tdName");
    row.appendChild(nameCell);

    const emailCell = document.createElement("td");
    emailCell.textContent = person.email;
    emailCell.setAttribute("class", "tdEmail");
    row.appendChild(emailCell);

    const actionCell = document.createElement("td");
    const editButton = document.createElement("button");
    const deleteButton = document.createElement("button");
    editButton.textContent = "Editar";
    editButton.setAttribute("id", "btn-edit");
    editButton.onclick = () => editPerson(person.id);
    actionCell.appendChild(editButton);

    deleteButton.textContent = "Deletar";
    deleteButton.setAttribute("id", "btn-delete");
    deleteButton.onclick = () => deletePerson(person.id);
    actionCell.appendChild(deleteButton);

    actionCell.setAttribute("class", "btn-container");

    row.appendChild(actionCell);
    tbody.appendChild(row);
  });
};

const editPerson = (id) => {
  alert(`Editar pessoa com ID: ${id}`);
};

const deletePerson = (id) => {
  alert(`Deletar pessoa com ID: ${id}`);
  deleteById(id);
};

function deleteById(id) {
  fetch(`http://localhost:8080/api/persons/${id}`, { method: "DELETE" })
    .then((res) => {
      if (res.ok) {
        alert(`Id: ${id} excluído com sucesso`);
        console.log("oi");

        fetchData();
      } else {
        // HTTP 4xx ou 5xx
        return res.text().then((errMessage) => {
          alert(`Erro ao excluir id: ${id}. Detalhes: ${errMessage}`);
        });
      }
    })
    .catch((ex) => {
      // outros erros
      alert(`Erro ao excluir id: ${id}. Detalhes: ${ex.message}`);
    });
}

// Inicialização
window.onload = fetchData;
