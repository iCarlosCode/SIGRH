function atualizarGrid(fornecedores) {
  const grid = document.getElementById("fornecedores-grid");
  grid.innerHTML = ""; // Limpar o grid existente

  fornecedores.forEach((fornecedor) => {
    const fornecedorDiv = document.createElement("div");
    fornecedorDiv.classList.add("fornecedor");

    const imagem = document.createElement("img");
    imagem.src = fornecedor.foto;
    imagem.alt = "company profile picture";
    imagem.classList.add("company-logo");
    fornecedorDiv.appendChild(imagem);

    const companyInfoDiv = document.createElement("div");
    companyInfoDiv.classList.add("company-information");

    const companyName = document.createElement("p");
    companyName.classList.add("company-name");
    companyName.textContent = fornecedor.nome;
    companyInfoDiv.appendChild(companyName);

    const additionalInfoDiv = document.createElement("div");
    additionalInfoDiv.classList.add("additional-information");

    const companyEmail = document.createElement("p");
    companyEmail.classList.add("company-email");
    const emailSpan = document.createElement("span");
    emailSpan.classList.add("email");
    emailSpan.textContent = fornecedor.email;
    companyEmail.textContent = "E-mail: ";
    companyEmail.appendChild(emailSpan);
    additionalInfoDiv.appendChild(companyEmail);

    const companyCNPJ = document.createElement("p");
    companyCNPJ.classList.add("company-CNPJ");
    const cnpjSpan = document.createElement("span");
    cnpjSpan.classList.add("cnpj");
    cnpjSpan.textContent = fornecedor.cnpj;
    companyCNPJ.textContent = "CNPJ: ";
    companyCNPJ.appendChild(cnpjSpan);
    additionalInfoDiv.appendChild(companyCNPJ);

    companyInfoDiv.appendChild(additionalInfoDiv);
    fornecedorDiv.appendChild(companyInfoDiv);

    const visualizarButton = document.createElement("button");
    visualizarButton.classList.add("btn-visualizar-fornecedor");
    visualizarButton.classList.add(fornecedor.cnpj);
    visualizarButton.textContent = "Visualizar";
    visualizarButton.addEventListener("click", function () {
      abreVisualizacao(fornecedor.cnpj);
    });
    fornecedorDiv.appendChild(visualizarButton);
    grid.appendChild(fornecedorDiv);
  });
}

/*define imagem de fundo atualização*/
const profileImgInputAtualizar = document.getElementById(
  "input-file-atualizar"
);
const inputVisualizarElement = document.getElementById(
  "input-file-holder-visualizar"
);
const inputFileAtualizar = document.getElementById(
  "input-file-holder-atualizar"
);

function defineFilePath() {
  const estiloComputado = window.getComputedStyle(inputFileAtualizar);
  const imagemDeFundo = estiloComputado.getPropertyValue("background-image");
  const splits1 = imagemDeFundo.split('"');
  console.log(splits1);
  const splits = splits1[1].split("/");
  console.log(imagemDeFundo, splits[4]);

  return splits[4];
}

let filePathAtualizar;
let mod = false;
profileImgInputAtualizar.addEventListener("change", function (event) {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.addEventListener("load", function () {
    const imageUrl = reader.result;
    inputFileAtualizar.style.backgroundColor = "transparent";
    console.log(`url("${imageUrl}")`);
    inputFileAtualizar.style.backgroundImage = `url("${imageUrl}")`;
    inputFileAtualizar.style.backgroundRepeat = "no-repeat";
  });

  if (file) {
    filePathAtualizar = `img/${file.name}`;
    mod = true;
    reader.readAsDataURL(file);
  }
});

/* define imagem de fundo cadastro */
const inputElement = document.getElementById("input-file-holder");
const inputFile = document.getElementById("input-file");
let filePath = "img/sem-foto.svg";

inputFile.addEventListener("change", function (event) {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.addEventListener("load", function () {
    const imageUrl = reader.result;
    inputElement.style.backgroundColor = "transparent";
    console.log(`url("${imageUrl}")`);
    inputElement.style.backgroundImage = `url("${imageUrl}")`;
    inputElement.style.backgroundRepeat = "no-repeat";
  });

  if (file) {
    filePath = `img/${file.name}`;
    reader.readAsDataURL(file);
  }
});

const grid = document.getElementById("fornecedores-grid");
function limparFornecedores() {
  grid.innerHTML = "";
}

function listaVazia() {
  const lista_vazia = document.createElement("p");
  lista_vazia.classList.add("nenhum-fornecedor");
  lista_vazia.textContent = "Nenhum fornecedor encontrado";
  if (grid.contains(lista_vazia)) return;
  grid.appendChild(lista_vazia);
}

function semFornecedores() {
  const vazio = document.createElement("p");
  vazio.classList.add("nenhum-fornecedor");
  vazio.textContent = "Nenhum fornecedor cadastrado";
  if (grid.contains(vazio)) return;
  grid.appendChild(vazio);
}

/*--- EVENT LISTENERS ---*/

/* abre atualização */

const containerAtualizarFornecedor = document.getElementById(
  "container-atualizar-fornecedor"
);
const labelCNPJAtualizar = document.getElementById("label-cnpj-atualizar");
const inputAtualizarNome = document.getElementById("atualizar-nome");
const inputAtualizarCNPJ = document.getElementById("atualizar-cnpj");
const inputAtualizarEmail = document.getElementById("atualizar-email");
const inputAtualizarEndereco = document.getElementById("atualizar-endereco");
const inputAtualizarTelefone = document.getElementById("atualizar-telefone");
const btnAtualizarFornecedor = document.getElementById("btn-alterar-dados");

btnAtualizarFornecedor.addEventListener("click", function (event) {
  event.preventDefault();
  containerVisualizarFornecedor.classList.remove(
    "container-visualizar-fornecedor"
  );
  containerVisualizarFornecedor.classList.add("hidden");

  containerAtualizarFornecedor.classList.add("container-atualizar-fornecedor");
  containerAtualizarFornecedor.classList.remove("hidden");

  inputFileAtualizar.style.backgroundImage =
    inputVisualizarElement.style.backgroundImage;
  inputFileAtualizar.style.backgroundRepeat = "no-repeat";
  inputAtualizarNome.value = nomeVisualizarForm.value;
  inputAtualizarCNPJ.value = cnpjVisualizarForm.value;
  inputAtualizarCNPJ.style.cursor = "auto";
  inputAtualizarCNPJ.style.outline = "none";

  inputAtualizarEmail.value = emailVisualizarForm.value;
  inputAtualizarEndereco.value = enderecoVisualizarForm.value;
  inputAtualizarTelefone.value = telefoneVisualizarForm.value;
});

/*
let URL = inputFileAtualizar.style.backgroundImage.split('"');
let filePathAtualizar = URL[1];
profileImgInputAtualizar.addEventListener("change", function (event) {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.addEventListener("load", function () {
    const imageUrl = reader.result;
    inputFileAtualizar.style.backgroundColor = "transparent";
    inputFileAtualizar.style.backgroundImage = `url("${imageUrl}")`;
    inputFileAtualizar.style.backgroundRepeat = "no-repeat";
  });

  if (file) {
    filePathAtualizar = `img/${file.name}`;
    reader.readAsDataURL(file);
  }
});


const btnSalvarAtualizacao = document.getElementById("btn-salvar-alteracao");
btnSalvarAtualizacao.addEventListener("click", function (event) {
  event.preventDefault();
});
*/

const modalConfirmacaoAtualizacao = document.getElementById(
  "modal-container-salvar-alteracao"
);
function confirmaAtualizacao() {
  segundaTelaDeSobreposicao.classList.remove("hidden");
  segundaTelaDeSobreposicao.classList.add("segundaCamadaSobreposicao");

  modalConfirmacaoAtualizacao.classList.remove("hidden");
  modalConfirmacaoAtualizacao.classList.add("modal-container-salvar-alteracao");

  setTimeout(function () {
    segundaTelaDeSobreposicao.classList.remove("segundaCamadaSobreposicao");
    segundaTelaDeSobreposicao.classList.add("hidden");

    modalConfirmacaoAtualizacao.classList.remove(
      "modal-container-salvar-alteracao"
    );
    modalConfirmacaoAtualizacao.classList.add("hidden");
  }, 3000);

  fechaAtualizacao();
  buscarTodos();
  fechaTelaDeVisualizacao();
}

/* fecha atualização */
const btnFecharAtualizacao = document.getElementById("fechar-atualizacao");
btnFecharAtualizacao.addEventListener("click", fechaAtualizacao);
function fechaAtualizacao() {
  containerAtualizarFornecedor.classList.add("hidden");
  containerAtualizarFornecedor.classList.remove(
    "container-atualizar-fornecedor"
  );

  containerVisualizarFornecedor.classList.remove("hidden");
  containerVisualizarFornecedor.classList.add(
    "container-visualizar-fornecedor"
  );
}

const btnRemover = document.getElementById("btn-apagar-fornecedor");
const modalRemocao = document.getElementById(
  "modal-container-confirmar-remocao"
);
const CNPJForm = document.getElementById("visualizar-cnpj");
btnRemover.addEventListener("click", function (event) {
  event.preventDefault();
  modalRemocao.classList.remove("hidden");
  modalRemocao.classList.add("modal-container-confirmar-remocao");

  segundaTelaDeSobreposicao.classList.remove("hidden");
  segundaTelaDeSobreposicao.classList.add("segundaCamadaSobreposicao");

  const btnConfirmarRemocao = document.getElementById("btn-confirmar-remocao");
  const btnCancelarRemocao = document.getElementById("btn-cancelar-remocao");

  btnCancelarRemocao.addEventListener("click", function () {
    modalRemocao.classList.remove("modal-container-confirmar-remocao");
    modalRemocao.classList.add("hidden");

    segundaTelaDeSobreposicao.classList.remove("segundaCamadaSobreposicao");
    segundaTelaDeSobreposicao.classList.add("hidden");
  });

  btnConfirmarRemocao.addEventListener("click", function () {
    const cnpj = CNPJForm.value;
    removePorCNPJ(cnpj);
  });
});

const modalFornecedorRemovido = document.getElementById(
  "modal-container-fornecedor-removido"
);
function confirmaRemocao() {
  modalRemocao.classList.remove("modal-container-confirmar-remocao");
  modalRemocao.classList.add("hidden");

  modalFornecedorRemovido.classList.add("modal-container-fornecedor-removido");
  modalFornecedorRemovido.classList.remove("hidden");

  setTimeout(function () {
    segundaTelaDeSobreposicao.classList.remove("segundaCamadaSobreposicao");
    segundaTelaDeSobreposicao.classList.add("hidden");

    modalFornecedorRemovido.classList.add("hidden");
    modalFornecedorRemovido.classList.remove(
      "modal-container-fornecedor-removido"
    );
  }, 3000);

  buscarTodos();
  fechaTelaDeVisualizacao();
}

/* abre tela de cadastro */
const btnAdicionar = document.getElementById("btn-adicionar");
const camadaSobreposicao = document.getElementById("camadaSobreposicao");
btnAdicionar.addEventListener("click", function (event) {
  escondeOverflow();
  const telaDeCadastro = document.getElementById("container-novo-fornecedor");

  telaDeCadastro.classList.remove("hidden");
  telaDeCadastro.classList.add("container-novo-fornecedor");
  camadaSobreposicao.classList.remove("hidden");
  camadaSobreposicao.classList.add("camadaSobreposicao");
});

/* abre confirmacao */
const telaDeConfirmacao = document.getElementById(
  "modal-container-fornecedor-adicionado"
);

/* limpa tela de cadastro */
const enderecoForm = document.getElementById("inscricao-endereco");
const emailForm = document.getElementById("inscricao-email");
const cnpjForm = document.getElementById("inscricao-cnpj");
const nomeForm = document.getElementById("inscricao-nome");
const telefoneForm = document.getElementById("inscricao-telefone");
function limpaCampos() {
  enderecoForm.value = "";
  emailForm.value = "";
  cnpjForm.value = "";
  nomeForm.value = "";
  telefoneForm.value = "";
  inputElement.style.backgroundColor = "#fff";
  inputElement.style.backgroundImage = "none";
}

/* fecha tela de cadastro */
const btnFecharCadastro = document.getElementById("fechar-inscricao");
btnFecharCadastro.addEventListener("click", fechaCadastro);

/* função para fechar a tela de cadastro com botao */
function fechaCadastro() {
  const telaDeCadastro = document.getElementById("container-novo-fornecedor");
  telaDeCadastro.classList.remove("container-novo-fornecedor");
  telaDeCadastro.classList.add("hidden");
  camadaSobreposicao.classList.add("hidden");
  camadaSobreposicao.classList.remove("camadaSobreposicao");

  buscarTodos();
  limpaCampos();
  mostraOverflow();
}

/* função para fechar cadastro após envio de formulário */
const segundaTelaDeSobreposicao = document.getElementById(
  "segunda-camada-sobreposicao"
);
function confirmaCadastro() {
  const telaDeCadastro = document.getElementById("container-novo-fornecedor");

  telaDeCadastro.classList.remove("container-novo-fornecedor");
  telaDeCadastro.classList.add("hidden");

  segundaTelaDeSobreposicao.classList.add("segundaCamadaSobreposicao");
  segundaTelaDeSobreposicao.classList.remove("hidden");

  telaDeConfirmacao.classList.remove("hidden");
  telaDeConfirmacao.classList.add("modal-container-fornecedor-adicionado");

  setTimeout(function () {
    telaDeConfirmacao.classList.remove("modal-container-fornecedor-adicionado");
    telaDeConfirmacao.classList.add("hidden");

    segundaTelaDeSobreposicao.classList.remove("segundaCamadaSobreposicao");
    segundaTelaDeSobreposicao.classList.add("hidden");
  }, 3000);

  fechaCadastro();
}

/* abre tela de visualização */
let btnsVisualizarFornecedor = document.getElementsByClassName(
  "btn-visualizar-fornecedor"
);
const containerVisualizarFornecedor = document.getElementById(
  "container-visualizar-fornecedor"
);
function abreVisualizacao(cnpj) {
  buscarPorCNPJ(cnpj);
  containerVisualizarFornecedor.classList.remove("hidden");
  containerVisualizarFornecedor.classList.add(
    "container-visualizar-fornecedor"
  );
  escondeOverflow();
}

/*constroi tela de visualização */
const enderecoVisualizarForm = document.getElementById("visualizar-endereco");
const emailVisualizarForm = document.getElementById("visualizar-email");
const cnpjVisualizarForm = document.getElementById("visualizar-cnpj");
const nomeVisualizarForm = document.getElementById("visualizar-nome");
const telefoneVisualizarForm = document.getElementById("visualizar-telefone");

const camerViewIcon = document.getElementById("camera-view-finder");
function constroiVisualizacao(fornecedor) {
  console.log(fornecedor.foto);

  enderecoVisualizarForm.value = fornecedor.endereço;
  emailVisualizarForm.value = fornecedor.email;
  cnpjVisualizarForm.value = fornecedor.cnpj;
  nomeVisualizarForm.value = fornecedor.nome;
  telefoneVisualizarForm.value = fornecedor.telefone;

  inputVisualizarElement.style.backgroundColor = "transparent";
  inputVisualizarElement.style.padding = "6rem 6.25rem";
  camerViewIcon.style.display = "none";
  inputVisualizarElement.style.backgroundImage = `url("${fornecedor.foto}")`;
  inputVisualizarElement.style.backgroundRepeat = "no-repeat";
  camadaSobreposicao.classList.add("camadaSobreposicao");
  camadaSobreposicao.classList.remove("hidden");
}

/* fecha tela de visualização */
const fechaVisualizacao = document.getElementById("fechar-visualizacao");
fechaVisualizacao.addEventListener("click", fechaTelaDeVisualizacao);
function fechaTelaDeVisualizacao() {
  containerVisualizarFornecedor.classList.remove(
    "container-visualizar-fornecedor"
  );
  containerVisualizarFornecedor.classList.add("hidden");
  camadaSobreposicao.classList.remove("camadaSobreposicao");
  camadaSobreposicao.classList.add("hidden");

  mostraOverflow();
}

/* abre tela de atualização 
const containerVisualizarFornecedor = document.getElementById(
  "container-visualizar-fornecedor"
);
const btnEditarFornecedor = document.getElementById("btn-alterar-dados");
btnEditarFornecedor.addEventListener("click", function (event) {
  containerVisualizarFornecedor.classList.remove("hidden");
  containerVisualizarFornecedor.classList.add("container-atualizar-fornecedor");
});

/* fecha tela de visualização
const btnFecharAtualizacao = document.getElementById("fechar-atualizacao");
btnFecharAtualizacao.addEventListener("click", function (event) {
  containerAtualizarFornecedor.classList.remove(
    "container-atualizar-fornecedor"
  );
  containerAtualizarFornecedor.classList.add("hidden");
});
*/

/* seta imagem selecionada como backgroud-image 
const inputFile = document.getElementById("input-file");
let filePath = "img/sem-foto.svg";

inputFile.addEventListener("change", function (event) {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.addEventListener("load", function () {
    const imageUrl = reader.result;
    inputElement.style.backgroundColor = "transparent";
    inputElement.style.backgroundImage = `url("${imageUrl}")`;
    inputElement.style.backgroundRepeat = "no-repeat";
  });

  if (file) {
    filePath = `img/${file.name}`;
    reader.readAsDataURL(file);
  }
});
*/

// Função de buscar todos os fornecedores
window.addEventListener("load", buscarTodos);

const mainTitle = document.getElementById("main-title");
const searchBar = document.getElementById("search-input");
searchBar.addEventListener("keyup", function (event) {
  event.preventDefault();

  mainTitle.textContent = "Resultados";

  buscarFornecedores();
});

// evita que, ao usuário pressionar enter, uma nova aba seja aberta
const searchForm = document.getElementById("search-form");
searchForm.addEventListener("submit", function (event) {
  event.preventDefault();
});

const searchSelect = document.getElementById("search-select");
searchSelect.addEventListener("change", function (event) {
  event.preventDefault();

  buscarFornecedores();
});

function escondeOverflow() {
  document.body.classList.add("modal-open");
}

function mostraOverflow() {
  document.body.classList.remove("modal-open");
}

/* ______REQUESTS______ */

function buscarFornecedores() {
  let searchSelect = document.getElementById("search-select").value;
  let searchFornecedor = document.getElementById("search-input").value;

  if (searchFornecedor == "") {
    buscarTodos();
    return;
  }

  let xhr = new XMLHttpRequest();

  xhr.open(
    "GET",
    `http://localhost:8080/fornecedores/search?search-select=
      ${searchSelect}&search-fornecedor=${searchFornecedor}`,
    true
  );

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let fornecedores = JSON.parse(this.responseText);

      if (fornecedores.length == 0) {
        limparFornecedores();
        listaVazia();
      } else {
        atualizarGrid(fornecedores);
      }
    }
  };

  xhr.send();
}

function buscarTodos() {
  let xhr = new XMLHttpRequest();

  xhr.open("GET", `http://localhost:8080/fornecedores/all`, true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let fornecedores = JSON.parse(this.responseText);

      if (fornecedores.length == 0) {
        limparFornecedores();
        semFornecedores();
        mainTitle.textContent = "Fornecedores Cadastrados";
      } else {
        atualizarGrid(fornecedores);
        mainTitle.textContent = "Fornecedores Cadastrados";
      }
    }
  };

  xhr.send();
}

const postForm = document.getElementById("inscricao-novo-fornecedor");

postForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const jsonBody = JSON.stringify({
    cnpj: cnpjForm.value,
    nome: nomeForm.value,
    endereço: enderecoForm.value,
    telefone: telefoneForm.value,
    email: emailForm.value,
    foto: filePath,
  });

  console.log(jsonBody);

  const url = "http://localhost:8080/fornecedores/add";

  const xhr = new XMLHttpRequest();

  // Abre a conexão com o método POST
  xhr.open("POST", url, true);

  // Define o tipo de conteúdo da requisição como application/json
  xhr.setRequestHeader("Content-Type", "application/json");

  // Define a função de callback para o evento de conclusão da requisição
  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        console.log("Requisição enviada com sucesso!");
        confirmaCadastro();
      } else {
        console.error("Ocorreu um erro na requisição:", xhr.status);
      }
    }
  };

  // Envia a requisição com o corpo JSON
  xhr.send(jsonBody);
});

function buscarPorCNPJ(cnpj) {
  const url = `http://localhost:8080/fornecedores/search?search-select=
  CNPJ&search-fornecedor=${cnpj}`;
  const xhr = new XMLHttpRequest();
  let fornecedor;
  xhr.open("GET", url, true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let fornecedores = JSON.parse(this.responseText);
      fornecedor = fornecedores[0];
      constroiVisualizacao(fornecedores[0]);
    }
  };

  xhr.send();

  return fornecedor;
}

function removePorCNPJ(cnpj) {
  url = `http://localhost:8080/fornecedores/remove/cnpj:${cnpj}`;
  const xhr = new XMLHttpRequest();

  xhr.open("DELETE", url, true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        // Requisição bem-sucedida
        confirmaRemocao();
      } else {
        // Tratar erros ou respostas diferentes de 200
        console.log("Ocorreu um erro durante a requisição DELETE.");
      }
    }
  };

  xhr.send();
}

const putForm = document.getElementById("atualizar-fornecedor");
putForm.addEventListener("submit", function (event) {
  event.preventDefault();

  if (!mod) {
    const fileName = defineFilePath();
    filePathAtualizar = `img/${fileName}`;
  }

  mod = false;

  const jsonBody = JSON.stringify({
    cnpj: inputAtualizarCNPJ.value,
    nome: inputAtualizarNome.value,
    endereço: inputAtualizarEndereco.value,
    telefone: inputAtualizarTelefone.value,
    email: inputAtualizarEmail.value,
    foto: filePathAtualizar,
  });

  console.log(jsonBody);

  const url = `http://localhost:8080/fornecedores/update/cnpj:${inputAtualizarCNPJ.value}`;

  const xhr = new XMLHttpRequest();

  // Abre a conexão com o método POST
  xhr.open("PUT", url, true);

  // Define o tipo de conteúdo da requisição como application/json
  xhr.setRequestHeader("Content-Type", "application/json");

  // Define a função de callback para o evento de conclusão da requisição
  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        console.log("Requisição enviada com sucesso!");
        confirmaAtualizacao();
      } else {
        console.error("Ocorreu um erro na requisição:", xhr.status);
      }
    }
  };

  // Envia a requisição com o corpo JSON
  xhr.send(jsonBody);
});
