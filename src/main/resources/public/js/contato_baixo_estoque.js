function start() {
  requestListarItensBaixoEstoque();
}

function requestListarItensBaixoEstoque(
  url = 'http://localhost:8080/api/armario/baixoEstoque'
) {
  console.log('Request de Listar Medicamentos Baixo Estoque');
  itensBaixoEstoqueLista.innerHTML = '';

  const response = fetch(url)
    .then(function (responseData) {
      return responseData.json(); // .json();
    })
    .then(function (jsonData) {
      //console.log(jsonData);
      const iitensBaixoEstoqueLista = document.getElementById('itensBaixoEstoqueLista');
      //console.log(jsonData);
      const items = jsonData.map(doc => {
        //querySnapshot.docs.map(doc => {

        return `<a href="#" id="${doc.nomeFornecedor + doc.nomeMedicamento}" class="list-group-item list-group-item-action flex-column align-items-start">
        <!--Dados aluno-->
        <div style="float: left; margin-left: 10px;">
            <!--NOME-->
            <span class="material-symbols-outlined">factory</span>
            <label class="user name NOME" style="vertical-align: top; max-width: 350px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${doc.nomeFornecedor}</label>
            <br>

            <!--Telefone-->
            <span class="material-symbols-outlined">phone</span>
            <label class="user matricula FREQ" style="vertical-align: top;">Telefone:</label>
            <label class="user matricula FREQ" style="vertical-align: top;">${doc.telefoneFornecedor}</label>
            <br>
        </div>
    
        <div style="float: right; margin-right: 10px;">
            <!--Medicamento-->
            <span class="material-symbols-outlined">medication</span>
            <label class="user matricula FREQ" style="vertical-align: top;">${doc.nomeMedicamento}</label>
            <br>

            <!--QUANTIDADE-->
            <span class="material-symbols-outlined">timeline</span>
            <strong><label class="user matricula FREQ" style="vertical-align: top;">${doc.quantidadeMedicamento} unidades</label></strong>
        </div>
    </a>`;
      });

      iitensBaixoEstoqueLista.innerHTML = items.join('');
    });
}
