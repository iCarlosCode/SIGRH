function start() {
  //requestPOST();
  //requestPOST();*/
  requestListarAlunos();
}

var itensSelecionadosListGroup = [];
var ultimoItemClicado;
//configurarSelecaoDosItensListGroupClick();
configurarSelecaoInicialDosItensListGroup();
configurarBtnDesselecionar();
configurarBtnSelecionarTudo();
configurarDialogEditar();

function configurarSelecaoInicialDosItensListGroup() {
  $('.list-group').on('contextmenu', '.list-group-item', function (event) {
    event.preventDefault();
    ultimoItemClicado = $(this);
    //console.log(mes)

    if (this.classList.contains('active')) {
      $(this).removeClass('active');
      itensSelecionadosListGroup.filter(removerItemSelecionado);
    } else {
      $(this).addClass('active'); //.siblings().removeClass('active');
      itensSelecionadosListGroup.push($(this));
    }
    mudarEstadosDaInterfaceNaSelecao(itensSelecionadosListGroup.length);
  });
}

function configurarBtnDesselecionar() {
  $('#desselecionarTudoBtn').on('click', function (event) {
    event.preventDefault();
    if (ultimoItemClicado != null) {
      ultimoItemClicado.siblings().removeClass('active');
      ultimoItemClicado.removeClass('active');
      mudarEstadosDaInterfaceNaSelecao(0);
      itensSelecionadosListGroup = [];
    }
    //pesquisar();
  });
}

function pesquisar() {
  var pesquisa = document.getElementById(
    'barraDePesquisaEnfermeiroInput'
  ).value;
  if (pesquisa !== '') {
    $('#desselecionarTudoBtn').click();
    switch (document.getElementById('barraDePesquisarEnfermeiroSelect').value) {
      case 'nome':
        console.log('BUSCANDO POR NOME');
        requestListarAlunos(
          `http://localhost:8080/api/buscar?nome=${pesquisa}`
        );
        console.log(`http://localhost:8080/api/buscar?nome=${pesquisa}`);
        break;
      case 'cpf':
        requestListarAlunos(
          `http://localhost:8080/api/buscar?nome=&cpf=${pesquisa}`
        );
        console.log(`http://localhost:8080/api/buscar?nome=&cpf=${pesquisa}`);
        break;
      case 'rg':
        requestListarAlunos(
          `http://localhost:8080/api/buscar?nome=&rg=${pesquisa}`
        );
        console.log(`http://localhost:8080/api/buscar?nome=&rg=${pesquisa}`);
        break;
      case 'telefone':
        requestListarAlunos(
          `http://localhost:8080/api/buscar?nome=&telefone=${pesquisa}`
        );
        console.log(
          `http://localhost:8080/api/buscar?nome=&telefone=${pesquisa}`
        );
        break;
      case 'lotacao':
        requestListarAlunos(
          `http://localhost:8080/api/buscar?nome=&lotacao=${pesquisa}`
        );
        console.log(
          `http://localhost:8080/api/buscar?nome=&lotacao=${pesquisa}`
        );
        break;
    }
  } else {
    requestListarAlunos();
  }
}
function configurarBtnSelecionarTudo() {
  $('#selecionarTudoBtn').on('click', function (e) {
    var id = itensSelecionadosListGroup[0].attr('id');
    console.log(id);
    itensSelecionadosListGroup = [];
    itensSelecionadosListGroup.push($(`#${id}`));
    itensSelecionadosListGroup[0].siblings().addClass('active');
    itensSelecionadosListGroup[0].siblings().each(function (index, element) {
      itensSelecionadosListGroup.push($(`#${element.id}`));
    });
    mudarEstadosDaInterfaceNaSelecao(itensSelecionadosListGroup.length);
  });
}

function configurarDialogEditar() {
  editEnfermeiroModalDialog.addEventListener('show.bs.modal', event => {
    console.log('TO COMECANDO A EDICAO');
    document.getElementById('nomeEditarEnfermeiroInput').value = getNomeISLG(
      itensSelecionadosListGroup[0]
    );
    document.getElementById('cpfEditarEnfermeiroInput').value = getCpfISLG(
      itensSelecionadosListGroup[0]
    );
    document.getElementById('rgEditarEnfermeiroInput').value = getRgISLG(
      itensSelecionadosListGroup[0]
    );
    document.getElementById('telefoneEditarEnfermeiroInput').value =
      getTelefoneISLG(itensSelecionadosListGroup[0]);
    document.getElementById('lotacaoEditarEnfermeiroInput').value =
      getLotacaoISLG(itensSelecionadosListGroup[0]).toLowerCase();
  });
}

/*
ID | Label
0  | Nome
2  | Cpf
4  | Rg
5  | Lotacao
6  | Telefone*/

function getNomeISLG(i) {
  return i.find('label')[0].innerHTML;
}

function getCpfISLG(i) {
  return i.find('label')[2].innerHTML;
}

function getRgISLG(i) {
  return i.find('label')[4].innerHTML;
}

function getLotacaoISLG(i) {
  return i.find('label')[5].innerHTML;
}

function getTelefoneISLG(i) {
  return i.find('label')[6].innerHTML;
}

function removerItemSelecionado(value, index, arr) {
  // If the value at the current array index matches the specified value (2)
  if (getCpfISLG(value) === getCpfISLG(ultimoItemClicado)) {
    // Removes the value from the original array

    arr.splice(index, 1);
    return true;
  }
  return false;
}
function mudarEstadosDaInterfaceNaSelecao(n) {
  var buttonsExtra = document.querySelectorAll('.buttonsExtra');
  var buttonsDiminuir = document.querySelectorAll('.bd');
  var buttonsAumentar = document.querySelectorAll('.ba');
  var buttonsFrequencia = document.querySelectorAll('.bf');

  if (n == 0) {
    //Caso barra de pesquisa esteja preechida
    ativacao = true;

    //escolherFunc();
    //configurarBtnToShearch();
    buttonsExtra[2].hidden = !ativacao;
    navBarTitulo.innerHTML =
      'SIGAE - Sistema de Gestão de Atividades de Enfermeiros';
  } else if (n == 1) {
    navBarTitulo.innerHTML = `${n} enfermeiro`;
    ativacao = false;
  }

  for (b = 0; b < buttonsExtra.length; b++) {
    buttonsExtra[b].hidden = ativacao;
  }

  addAluno.hidden = !ativacao;

  if (n > 1) {
    buttonsExtra[1].hidden = !ativacao;
    navBarTitulo.innerHTML = `${n} enfermeiros`;
  }
}

function requestListarAlunos(
  url = 'http://localhost:8080/api/listar_enfermeiros'
) {
  console.log('Request de Listar Alunos');
  enfermeirosLista.innerHTML = '';

  const response = fetch(url)
    .then(function (responseData) {
      return responseData.json(); // .json();
    })
    /*.then(function (jsonData) {
      return JSON.parse(jsonData.replace('[{ ', '[{ "').replaceAll(', { ', ', { "').replaceAll("', ", '", "').replaceAll("='", '":"').replaceAll("'", '"'));
    })*/
    .then(function (jsonData) {
      console.log(jsonData);
      const enfermeirosLista = document.getElementById('enfermeirosLista');
      console.log(jsonData);
      const items = jsonData.map(doc => {
        //querySnapshot.docs.map(doc => {

        return `<a href="#" id=${doc.cpf}  
         class="list-group-item list-group-item-action flex-column align-items-start">
         <!--Dados aluno-->
         <div style="float: left; margin-left: 10px;">
             <!--NOME-->
             <span class="material-symbols-outlined">person</span>
             <label " class= "user name NOME" style="vertical-align: top; max-width: 350px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${doc.nome}</label>
             <br>

             <!--CPF-->
             <span class="material-symbols-outlined">money</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">CPF:</label>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.cpf}</label>
             <br>
             
             <!--RG-->
             <span class="material-symbols-outlined">pin</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">RG:</label>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.rg}</label>
         </div>
     
         <div style="float: right; margin-right: 10px;">
             <br>

             <!--LOTAÇÃO-->
             <span class="material-symbols-outlined">location_on</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.lotação}</label>
             <br>

             <!--TELEFONE-->
             <span class="material-symbols-outlined">call</span>
             <label class= "user matricula FREQ" style="vertical-align: top;">${doc.telefone}</label>
         </div>
     </a>`;
      });

      enfermeirosLista.innerHTML = items.join('');
    });
  /*.catch(function (e) {
      console.log('Erro: ' + e);
    });*/
}

function requestCadastrarAluno() {
  var nome = document.getElementById('nomeAdicionarEnfermeiroInput').value;
  var cpf = document.getElementById('cpfAdicionarEnfermeiroInput').value;
  var rg = document.getElementById('rgAdicionarEnfermeiroInput').value;
  var select = document.getElementById('lotacaoAdicionarEnfermeiroInput');
  var lotacao = select.options[select.selectedIndex].text;
  var telefone = document.getElementById(
    'telefoneAdicionarEnfermeiroInput'
  ).value;

  if (cpf != '' && nome != '' && rg != '' && telefone != '' && lotacao != '') {
    const headers = {
      'Content-Type': 'application/json',
    };

    const init = {
      method: 'POST',
      headers: headers,
      body: JSON.stringify({
        nome: nome,
        telefone: telefone,
        rg: rg,
        cpf: cpf,
        lotação: lotacao,
      }),
    };

    const response = fetch('http://localhost:8080/api/cadastrar', init)
      .then(function (responseData) {
        return responseData.json();
      })
      .then(function (jsonData) {
        console.log(jsonData);
        requestListarAlunos();
        return jsonData;
      })
      .catch(function (e) {
        console.log('Erro: ' + e);
      });
  }
}

function requestEditarAluno() {
  var nome = document.getElementById('nomeEditarEnfermeiroInput').value;
  var cpf = document.getElementById('cpfEditarEnfermeiroInput').value;
  var rg = document.getElementById('rgEditarEnfermeiroInput').value;
  var select = document.getElementById('lotacaoEditarEnfermeiroInput');
  var lotacao = select.options[select.selectedIndex].text;
  var telefone = document.getElementById('telefoneEditarEnfermeiroInput').value;
  var cpfAntigo = itensSelecionadosListGroup[0][0].id;

  //http://localhost:8080/api/editar?nome=Beltrano&telefone=7588888888&rg=6664567&cpf=66645678912&lotação=Enfermaria&cpfAntigo=12345678910

  const response = fetch(
    `http://localhost:8080/api/editar?nome=${nome}&telefone=${telefone}&rg=${rg}&cpf=${cpf}&lotação=${lotacao}&cpfAntigo=${cpfAntigo}`,
    { method: 'PATCH' }
  )
    .then(function (responseData) {
      return responseData.json();
    })
    .then(function (jsonData) {
      console.log(jsonData);
      requestListarAlunos();
      return jsonData;
    })
    .catch(function (e) {
      console.log('Erro: ' + e);
    });
}

function requestDeletarAluno() {
  //http://localhost:8080/api/remover?nome=&cpf=66645678910

  itensSelecionadosListGroup.forEach(element => {
    var cpf = element[0].id;
    const response = fetch(
      `http://localhost:8080/api/remover?nome=&cpf=${cpf}`,
      {
        method: 'DELETE',
      }
    )
      .then(function (responseData) {
        return responseData.json();
      })
      .then(function (jsonData) {
        console.log(jsonData);
        requestListarAlunos();
        return jsonData;
      })
      .catch(function (e) {
        console.log('Erro: ' + e);
      });
  });
  requestListarAlunos();
  $('#desselecionarTudoBtn').click();
}

function requestPOST() {
  console.log('Função POST foi ativadaaaa');
  const headers = {
    'Content-Type': 'application/json',
  };
  const init = {
    method: 'POST',
    headers: headers,
    body: JSON.stringify({
      nome: 'Fulano',
      telefone: '75999999999',
      rg: '1234567',
      cpf: '12345678910',
      lotação: 'Apartamentos',
    }),
  };
  const response = fetch('http://localhost:8080/api/cadastrar', init)
    .then(function (responseData) {
      return responseData.json();
    })
    .then(function (jsonData) {
      console.log(jsonData);
      return jsonData;
    })
    .catch(function (e) {
      console.log('Erro: ' + e);
    });
}
