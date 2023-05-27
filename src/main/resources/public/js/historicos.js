function start() {
  //requestPOST();
  //requestPOST();*/
  requestListarHistoricos();
  requestPopulateFormOptions();
  medicamentoRetirarMedicamento.addEventListener("input", function (e) {
    fetch(`http://localhost:8080/api/armario/buscar?codigo=${this.value}`, {method: 'GET'})
      .then(response => response.json())
      .then(result => {
  
        quantidadeRetirarMedicamento.max = result.quantidade;
        quantidadeMax.innerHTML = result.quantidade;
        console.log(result.quantidade);
      })
      .catch(error => console.log('error', error));
  });
}

var itensSelecionadosListGroup = [];
var ultimoItemClicado;
//configurarSelecaoDosItensListGroupClick();
//configurarSelecaoInicialDosItensListGroup();
//configurarBtnDesselecionar();
//configurarBtnSelecionarTudo();
//configurarDialogEditar();
/*
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
}*/

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
    'barraDePesquisaHistoricoInput'
  ).value;
  if (pesquisa !== '') {
    $('#desselecionarTudoBtn').click();
    switch (document.getElementById('barraDePesquisarHistoricoSelect').value) {
      case 'nomeEnfermeiro':
        console.log('BUSCANDO POR NOME');
        requestListarHistoricos(
          `http://localhost:8080/api/buscar_historico?nomeEnfermeiro=${pesquisa}`
        );
        console.log(`http://localhost:8080/api/buscar_historico?nomeEnfermeiro=${pesquisa}`);
        break;
      case 'cpf':
        requestListarHistoricos(
          `http://localhost:8080/api/buscar_historico?nomeEnfermeiro=&cpf=${pesquisa}`
        );
        console.log(`http://localhost:8080/api/buscar_historico?nomeEnfermeiro=&cpf=${pesquisa}`);
        break;
      case 'nomeMedicamento':
        requestListarHistoricos(
          `http://localhost:8080/api/buscar_historico?nomeEnfermeiro=&nomeMedicamento=${pesquisa}`
        );
        console.log(`http://localhost:8080/api/buscar_historico?nomeEnfermeiro=&nomeMedicamento=${pesquisa}`);
        break;
      case 'codigo':
        requestListarHistoricos(
          `http://localhost:8080/api/buscar_historico?nomeEnfermeiro=&codigo=${pesquisa}`
        );
        console.log(
          `http://localhost:8080/api/buscar_historico?nomeEnfermeiro=&codigo=${pesquisa}`
        );
        break;
    }
  } else {
    requestListarHistoricos();
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

function requestListarHistoricos(
  url = 'http://localhost:8080/api/listar_historicos'
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
      //console.log(jsonData);
      const enfermeirosLista = document.getElementById('enfermeirosLista');
      //console.log(jsonData);
      const items = jsonData.map(doc => {
        //querySnapshot.docs.map(doc => {

        return `<a href="#" id="${doc.cpfEnfermeiro + doc.codigoMedicamento}" class="list-group-item list-group-item-action flex-column align-items-start">
        <!--Dados aluno-->
        <div style="float: left; margin-left: 10px;">
            <!--NOME-->
            <span class="material-symbols-outlined">person</span>
            <label class="user name NOME" style="vertical-align: top; max-width: 350px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${doc.nomeEnfermeiro}</label>
            <br>

            <!--CPF-->
            <span class="material-symbols-outlined">money</span>
            <label class="user matricula FREQ" style="vertical-align: top;">CPF:</label>
            <label class="user matricula FREQ" style="vertical-align: top;">${doc.cpfEnfermeiro}</label>
            <br>
        </div>
    
        <div style="float: right; margin-right: 10px;">
            <!--Medicamento-->
            <span class="material-symbols-outlined">medication</span>
            <label class="user matricula FREQ" style="vertical-align: top;">${doc.nomeMedicamento}</label>
            <br>

            <!--QUANTIDADE-->
            <span class="material-symbols-outlined">timeline</span>
            <label class="user matricula FREQ" style="vertical-align: top;">${doc.quantidadeMedicamento} unidade${doc.quantidadeMedicamento != 1 ? 's':''}</label>
        </div>
    </a>`;
      });

      enfermeirosLista.innerHTML = items.join('');
    });
  /*.catch(function (e) {
      console.log('Erro: ' + e);
    });*/
}

function requestPopulateFormOptions() {
  fetch("http://localhost:8080/api/listar_enfermeiros", {method: 'GET'})
    .then(response => response.json())
    .then(result => {
      //console.log(result);
      result.forEach(item => {
        let option = document.createElement('option');
        option.value = item.cpf;
        option.innerHTML = item.nome; 
        enfemeirosList.appendChild(option);
      });
    })
    .catch(error => console.log('error', error));

  fetch("http://localhost:8080/api/armario/get/medicamentos", {method: 'GET'})
    .then(response => response.json())
    .then(result => {
      //console.log(result);
      result.forEach(item => {
        let option = document.createElement('option');
        option.value = item.codigo;
        option.innerHTML = item.nome; 
        medicamentosList.appendChild(option);
      });
    })
    .catch(error => console.log('error', error));

}



function r2equestListarHistoricos(
  url = 'http://localhost:8080/api/listar_historicos'
) {
  console.log('Request de Listar Alunos');
  var nomeEnfermeiro;
  var nomeMedicamento;
  enfermeirosLista.innerHTML = '';

  const response = fetch(url)
    .then(function (responseData) {
      return responseData.json(); // .json();
    })
    /*.then(function (jsonData) {
      return JSON.parse(jsonData.replace('[{ ', '[{ "').replaceAll(', { ', ', { "').replaceAll("', ", '", "').replaceAll("='", '":"').replaceAll("'", '"'));
    })*/
    .then(function (jsonData) {
      // CAMADA 1🔴
      //console.log("CAMADA 1🔴");
      //console.log(jsonData);
      const enfermeirosLista = document.getElementById('enfermeirosLista');
      //console.log(jsonData);
      const items = jsonData.map(doc => {
        //querySnapshot.docs.map(doc => {


        
        const responseNomeEnfermeiro = fetch("http://localhost:8080/api/buscar?nome=&cpf=12345678910" //+ doc.cpf)
        ).then(response => response.json())
        .then(result => {
          nomeEnfermeiro = result[0].nome;
        })
        .then(() =>{
            /*// CAMADA 2🔴
                console.log("CAMADA 2🔴");
                const responseNomeMedicamento = fetch("http://localhost:8080/api/armario/buscar?codigo=" + doc.codigo)
                .then(function (responseData) {
                  return responseData.json(); // .json();
                })
                .then(function (jsonData) {
                    const nomeMedicamento  = jsonData.nome;
                })*/
                const responseNomeMedicamento = fetch("http://localhost:8080/api/armario/buscar?codigo=L0004"
                ).then(response => response.json())
                .then(result => {
                    nomeMedicamento = result.nome;
                })
                .then(() => {
                // CAMADA 3🔴
                console.log("CAMADA 3🔴");
                          return `<a href="#" id="${doc.cpfEnfermeiro + doc.codigoMedicamento}" class="list-group-item list-group-item-action flex-column align-items-start">
                      <!--Dados aluno-->
                      <div style="float: left; margin-left: 10px;">
                          <!--NOME-->
                          <span class="material-symbols-outlined">person</span>
                          <label class="user name NOME" style="vertical-align: top; max-width: 350px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">Pedro Carlos de Oliveira dos Santos</label>
                          <br>
            
                          <!--CPF-->
                          <span class="material-symbols-outlined">money</span>
                          <label class="user matricula FREQ" style="vertical-align: top;">CPF:</label>
                          <label class="user matricula FREQ" style="vertical-align: top;">${nomeEnfermeiro}</label>
                          <br>
                      </div>
                  
                      <div style="float: right; margin-right: 10px;">
                          <!--Medicamento-->
                          <span class="material-symbols-outlined">medication</span>
                          <label class="user matricula FREQ" style="vertical-align: top;">${nomeMedicamento}</label>
                          <br>
            
                          <!--QUANTIDADE-->
                          <span class="material-symbols-outlined">timeline</span>
                          <label class="user matricula FREQ" style="vertical-align: top;">${doc.quantidadeMedicamento} unidade${doc.quantidadeMedicamento != 1 ? 's':''}</label>
                      </div>
                  </a>`;
                });
            });
        });
        return items;
      })
      .then((items) => {
        enfermeirosLista.innerHTML = items.join('');
      });

      
}
  /*.catch(function (e) {
      console.log('Erro: ' + e);
    });*/




function obterDataListOuterText(list, value)
{
    [...list].forEach(function (item) {
        if (item.value === value) {
          console.log(`O item de ${item.value} tem o outerText de ${item.outerText}`);
            return item.outerText;
        }
    });
}

function requestRetirarMedicamento() {
  
  var myHeaders = new Headers();
  var nomeEnfermeiro = "";
  var nomeMedicamento = "";
  myHeaders.append("Content-Type", "application/json");
  
  //console.log(obterDataListOuterText(enfemeirosList.options, enfermeiroRetirarMedicamento.value));

  [...enfemeirosList.options].forEach(function (item) {
    if (item.value === enfermeiroRetirarMedicamento.value) {
      nomeEnfermeiro = item.outerText;
    }
  });
  
  [...medicamentosList.options].forEach(function (item) {
    if (item.value === medicamentoRetirarMedicamento.value) {
      nomeMedicamento = item.outerText;
    }
  });


  var raw = JSON.stringify({
    "nomeEnfermeiro": nomeEnfermeiro,
    "cpfEnfermeiro": enfermeiroRetirarMedicamento.value,
    "nomeMedicamento": nomeMedicamento,
    "codigoMedicamento": medicamentoRetirarMedicamento.value,
    "quantidadeMedicamento": quantidadeRetirarMedicamento.value
  });


  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
  };

  fetch("http://localhost:8080/api/retirar_medicamento", requestOptions)
    .then(response => response.text())
    .then(result => {requestListarHistoricos();})//console.log(result))
    .catch(error => console.log('error', error));
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
        requestListarHistoricos();
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
      requestListarHistoricos();
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
        requestListarHistoricos();
        return jsonData;
      })
      .catch(function (e) {
        console.log('Erro: ' + e);
      });
  });
  requestListarHistoricos();
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
