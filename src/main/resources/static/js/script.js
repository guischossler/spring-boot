const persons = [];

const addElementHtml = (tagHtml, attributeHtml = null, attributeNameHtml = null) => {
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
    headerRow.appendChild(th);
  });
  thead.appendChild(headerRow);

  table.appendChild(thead);
  table.appendChild(tbody);
  main.appendChild(table);

  return table;
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
    row.appendChild(idCell);

    const nameCell = document.createElement("td");
    nameCell.textContent = person.name;
    row.appendChild(nameCell);

    const emailCell = document.createElement("td");
    emailCell.textContent = person.email;
    row.appendChild(emailCell);

    const actionCell = document.createElement("td");
    const editButton = document.createElement("button");
    const deleteButton = document.createElement("button");
    editButton.textContent = "Editar";
    editButton.onclick = () => editPerson(person.id);
    actionCell.appendChild(editButton);

    deleteButton.textContent = "Deletar";
    deleteButton.onclick = () => deletePerson(person.id);
    actionCell.appendChild(deleteButton);

    row.appendChild(actionCell);
    tbody.appendChild(row);
  });
};

const editPerson = (id) => {
  alert(`Editar pessoa com ID: ${id}`);
};

const deletePerson = (id) => {
    alert(`Deletar pessoa com ID: ${id}`);
};

// Inicialização
addElementHtml("div", "class", "main");
window.onload = fetchData;
