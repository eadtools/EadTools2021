function exibeApp(){
    let rowApp = document.getElementById('colApp');
    let texto = '';
    let pesquisa = document.getElementById('pesquisa_app');
    let dadosp = JSON.parse(this.responseText);
    dadosp = dadosp.aplicativos;
    let filtroNota = document.getElementById('filtroNota');
    let filtroCategoria = document.getElementById('filtroCategoria');
    let filtroDispositivo = document.getElementById('filtroDispositivo');
    let filtroPago = document.getElementById('filtroPago');
    let dados = dadosp.filter(item => item.Nota > filtroNota.options[filtroNota.selectedIndex].value);
    dados = dados.filter(item => item.Dispositivo.includes(filtroDispositivo.options[filtroDispositivo.selectedIndex].value));
    dados = dados.filter(item => item.Categoria.includes(filtroCategoria.options[filtroCategoria.selectedIndex].value));
    dados = dados.filter(item => item.Pago.includes(filtroPago.options[filtroPago.selectedIndex].value));
    if(pesquisa.value != ""){
        dados = dados.filter(item => item.Nome.includes(pesquisa.value));
    }
    for (i=0; i<dados.length;i++){
        let app = dados[i];
        let dispcomplete = '';
        for(j=2; j<app.Dispositivo.length;j++){
            dispcomplete = dispcomplete + `/${app.Dispositivo[j]}`;
        }
        texto = texto + `
            <div class="applicativo">
            <div class="row" id="app">
            <div class="col-12 col-md-3" id="picapp">
                <img id="imageapli" src="${app.img}" height="150" width="150" alt=""><br>
                <p id="nota"><span>Nota:</span>
                <span>${app.Nota}</span></p>
            </div>
            <div class="col-md-9 col-12" id="infoapli">
                <h2>${app.Nome}</h2>
                <p><span>Dispositivo:</span> ${app.Dispositivo[1]+dispcomplete}</p>
                <p><span>Descrição:</span> ${app.Descrição}</p>
                <div class="mb-3">
                    <a class="btn btn-success" onclick="AppPage(${app.id})" role="button">Ver mais</a>
                </div>
            </div>
                <div id="linha-horizontal"></div>
            </div>
        `;
    };

    rowApp.innerHTML = texto;
}

function initApp(){
    let rowApp = document.getElementById('itemsdocar');
    let texto = '';
    let dadosp = JSON.parse(this.responseText);
    dados = dadosp.aplicativos;
    for (i=0; i<4;i++){
        let app = dados[i];
        texto = texto + `
            <div class="col-sm-3">

            <div class="card">
            <div class="card-body">
                <img src="${app.img}" height="100" width="100" alt="">
                <h5 class="card-title">${app.Nome}</h5>
                <p class="card-text"><span>Descrição:</span> ${app.Descrição} <br><span>
                    Nota:</span> ${app.Nota}/10</p>
                <a onclick="AppPage(${app.id})" class="btn btn-success">Ver</a>
            </div>
            </div>
        </div>
        `;
    };

    rowApp.innerHTML = texto;
}

function listarApps (){
    let xhr = new XMLHttpRequest ();
    xhr.onload = exibeApp;
    xhr.open('GET','db.json');
    xhr.send();
}

function destaqueApps (){
    let xhr2 = new XMLHttpRequest ();
    xhr2.onload = initApp;
    xhr2.open('GET','db.json');
    xhr2.send();
}

function AppPage(id2){
    let xhr3 = new XMLHttpRequest();
    xhr3.onload = function Page(){
        let w = window.open('app.html', '_blank');
        let texto = '';
        let texto2 = '';
        for (var i = 0; i < db_comentarios.comentarios.length; i++){
            let c = db_comentarios.comentarios[i]
            if(c.id_app == id2){
                texto2 = texto2+`
                <h5>${c.autor}</h5>
                <textarea disabled name="comment" id="" cols="10" rows="5">${c.texto}</textarea>
                `;
            }
        }
        let dispcomplete = '';
        let dadosp = JSON.parse(this.responseText);
        dados = dadosp.aplicativos;

        let app = dados[id2-1];
        for(j=2; j<app.Dispositivo.length;j++){
            dispcomplete = dispcomplete + `/${app.Dispositivo[j]}`;
        }
        texto = texto + `
        <!doctype html>
        <html lang="pt-br">
          <head>
            <title>${app.Nome}</title>
            <!-- Required meta tags -->
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
            <script src="https://kit.fontawesome.com/4498db3338.js" crossorigin="anonymous"></script>
            <link href="style.css" rel="stylesheet">
            <body>
                <div class="header">
                    <nav class="navbar navbar-expand-lg navbar-dark fixed-top">
                        <div class="container-fluid">
                          <a class="navbar-brand" href="#"><img id="imagemi" src="eadtools_white-removebg.png" class="img" alt="..."></a>
                          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                          </button>
                          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                              <a class="nav-link" aria-current="page" href="index.html">Home</a>
                              <a class="nav-link active" aria-current="page" href="apli.html">Aplicações</a>
                              <a class="nav-link" aria-current="page" id="loglink" href="login.html">Login</a>
                            </div>
                          </div>
                        </div>
                      </nav>
                </div>
                <div class="container-fluid" id="containerGeral">
                    <div class="row">
                        <div class="col-12 col-md-2" id="filtros">
                          <div class="appinfo_apppage" id="appinfo_apppage">
                            <img src="${app.img}" width="170" alt="">
                            <h2>${app.Nome}</h2>
                            <p>Nota:</p>
                            <p><span>${app.Nota}/10</span></p>
                            <p>Dispositivo:</p>
                            <p>${app.Dispositivo[1]+dispcomplete}</p>
                          </div>
                        </div>
                        <div class="col">
                            <div class="applicativo" id="colaApp">
                                <div class="row" id="app">
                                    <h3>Sobre o App</h3>
                                    <p>${app.Descrição}</p>
                                    ${app.video}
                                    <h3>Onde encontrar</h3>
                                    <p>Vá para a página da aplicação clicando aqui: <a href="${app.local}">${app.local}</a></p>
                                    <h3>Recursos</h3>
                                    <p>${app.dica}</p>
                                    <div class="row" id="commentarea">
                                    <h3>Comentários</h3>
                                    <textarea name="comment" id="mycomment" cols="10" rows="5"></textarea>
                                    <a href="#" id="comentar" onclick="comentar(${id2})" class="btn btn-success">Comentar</a>
                                    ${texto2}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <script src="script.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
          </body>
        </html>
        `;
        w.document.write(`<p>${texto}</p>`);
        verifyLogged();
    };
    xhr3.open('GET','db.json');
    xhr3.send();
}

document.onload = listarApps();
document.onload = destaqueApps();


//////////////////////////////////////////////////////////////////////////////

//
//
// Disciplina: Trabalho Interdisciplinar - Aplicações Web
// Professor: Rommel Vieira Carneiro (rommelcarneiro@gmail.com)
//
// Código LoginApp utilizado como exemplo para alunos de primeiro período 


// Página inicial de Login
const LOGIN_URL = "login.html";

//functions latter added

function verifyLogged(){
    var logado = localStorage.getItem('logado');
    if (!logado) {  
        logado = 0;
        localStorage.setItem('logado', JSON.stringify (logado));
    }
    else  {
        logado = JSON.parse(logado);
        let navlog = document.getElementById("loglink");
        var comentar_permit = document.getElementById("mycomment");
        var comentar_button = document.getElementById("comentar");
        if(logado == 1){
            let navlog = document.getElementById("loglink");
            var comentar_permit = document.getElementById("mycomment");
            navlog.innerText = "Sair";
            navlog.setAttribute("href","index.html");
            navlog.setAttribute("onclick","logoutUser ()");
        }
        else{
            navlog.innerText = "Login";
            navlog.setAttribute("href","login.html");
            navlog.setAttribute("onclick","");
            comentar_permit.setAttribute("disabled", "true")
            comentar_button.setAttribute("disabled","true");
        }
    }
};

// Objeto para o banco de dados de usuários baseado em JSON
var db_usuarios = {};
var db_comentarios = {};
// Objeto para o usuário corrente
var usuarioCorrente = {};

// Comentarios
const commentsIniciais = {
    comentarios: [
        { "id": 14, "id_app": 1, "autor": "Ana Paula K.", "texto": "O melhor aplicativo que utilizei até agora. Muito útil para dividir as turmas em grupos, com a função de canais separados."},
        { "id": 16, "id_app": 1, "autor": "Roberto Mancine", "texto": "Um bom aplicativo. Recomendo que se utilize a versão para desktop, pois trava menos."},
        { "id": 26, "id_app": 2, "autor": "Giuseppe Guardiola", "texto": "Uma ótima plataforma para postar atividades para os alunos."},
        { "id": 36, "id_app": 2, "autor": "Giordana Esperandio", "texto": "Fácil de usar, mas não gostei que ele não tem reuniões no app, as chamadas abrem no Meet."},
        { "id": 16, "id_app": 3, "autor": "Adriana Gaudino", "texto": "Estou sempre usando esse aplicativo para as aulas, é o mais leve para o meu computador."},
        { "id": 16, "id_app": 3, "autor": "José Mourim", "texto": "Achei muito complicado. Não cabem quantas pessoas eu quero colocar."},
        { "id": 16, "id_app": 4, "autor": "Sylvio Mendes", "texto": "Muito bom. Só evito passar dos 5 minutos de vídeo, para não quer que cortar em dois, nem pagar."},
        { "id": 16, "id_app": 4, "autor": "Alessandra Kirmse", "texto": "Assinei a versão paga, o aplicativo melhorou muito. A edição não é muito boa, mas ele realmente serve para gravar coisas simples."},
        { "id": 16, "id_app": 5, "autor": "Luís Felipe", "texto": "Recebi muitos exercícios bons."},
        { "id": 16, "id_app": 6, "autor": "Angela Alves", "texto": "O app é muito bom para chamadas, o único problema é que para os iPads ou tablets o meet não atualiza o layout é horrível para tablet!"},
        { "id": 16, "id_app": 6, "autor": "Ramon Menezes", "texto": "Acho que o app poderia dar algumas opções como por exemplo: usar como web Cam no Pc, configurar a qualidade da câmera e configuração que melhorasse o áudio gando de entrada como de saída."},
        { "id": 16, "id_app": 7, "autor": "Bruno Formiga", "texto": "Vários recursos interessantes, mesmo que o layout pareça meio bagunçado."},
        { "id": 16, "id_app": 7, "autor": "Isabella Pagliari", "texto": "Amei! Textos suuuuuper legais e várias aulas."},
        { "id": 16, "id_app": 8, "autor": "Joel Santana", "texto": "Bacana, uma pena que só tem em inglês."},
    ]
};

// função para gerar códigos randômicos a serem utilizados como código de usuário
// Fonte: https://stackoverflow.com/questions/105034/how-to-create-guid-uuid
function generateUUID() { // Public Domain/MIT
    var d = new Date().getTime();//Timestamp
    var d2 = (performance && performance.now && (performance.now()*1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16;//random number between 0 and 16
        if(d > 0){//Use timestamp until depleted
            r = (d + r)%16 | 0;
            d = Math.floor(d/16);
        } else {//Use microseconds since page-load if supported
            r = (d2 + r)%16 | 0;
            d2 = Math.floor(d2/16);
        }
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}


// Dados de usuários para serem utilizados como carga inicial
const dadosIniciais = {
    usuarios: [
        { "id": generateUUID (), "login": "admin", "senha": "123", "nome": "Administrador do Sistema", "email": "admin@abc.com"},
        { "id": generateUUID (), "login": "user", "senha": "123", "nome": "Usuario Comum", "email": "user@abc.com"},
    ]
};


// Inicializa o usuarioCorrente e banco de dados de usuários da aplicação de Login
function initLoginApp () {
    // PARTE 1 - INICIALIZA USUARIOCORRENTE A PARTIR DE DADOS NO LOCAL STORAGE, CASO EXISTA
    usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
    if (usuarioCorrenteJSON) {
        usuarioCorrente = JSON.parse (usuarioCorrenteJSON);
    }
    
    // PARTE 2 - INICIALIZA BANCO DE DADOS DE USUÁRIOS
    // Obtem a string JSON com os dados de usuários a partir do localStorage
    var usuariosJSON = localStorage.getItem('db_usuarios');
    var commentsJSON = localStorage.getItem('db_comentarios');
    // Verifica se existem dados já armazenados no localStorage
    if (!usuariosJSON) {  // Se NÃO há dados no localStorage
        
        // Informa sobre localStorage vazio e e que serão carregados os dados iniciais
        alert('Dados de usuários não encontrados no localStorage. \n -----> Fazendo carga inicial.');

        // Copia os dados iniciais para o banco de dados 
        db_usuarios = dadosIniciais;

        // Salva os dados iniciais no local Storage convertendo-os para string antes
        localStorage.setItem('db_usuarios', JSON.stringify (dadosIniciais));
    }
    else  {  // Se há dados no localStorage
        
        // Converte a string JSON em objeto colocando no banco de dados baseado em JSON
        db_usuarios = JSON.parse(usuariosJSON);    
    }

    if (!commentsJSON) {  // Se NÃO há dados no localStorage
        
        // Informa sobre localStorage vazio e e que serão carregados os dados iniciais
        alert('Dados de comentários não encontrados no localStorage. \n -----> Fazendo carga inicial.');

        // Copia os dados iniciais para o banco de dados 
        db_comentarios = commentsIniciais;

        // Salva os dados iniciais no local Storage convertendo-os para string antes
        localStorage.setItem('db_comentarios', JSON.stringify (commentsIniciais));
    }
    else  {  // Se há dados no localStorage
        
        // Converte a string JSON em objeto colocando no banco de dados baseado em JSON
        db_comentarios = JSON.parse(commentsJSON);    
    }
};


// Verifica se o login do usuário está ok e, se positivo, direciona para a página inicial
function loginUser (login, senha) {
    
    // Verifica todos os itens do banco de dados de usuarios 
    // para localizar o usuário informado no formulario de login
    for (var i = 0; i < db_usuarios.usuarios.length; i++) {
        var usuario = db_usuarios.usuarios[i];
        
        // Se encontrou login, carrega usuário corrente e salva no Session Storage
        if (login == usuario.login && senha == usuario.senha) {
            logado = 1;
            localStorage.setItem('logado', JSON.stringify (logado));
            //
            usuarioCorrente.id = usuario.id;
            usuarioCorrente.login = usuario.login;
            usuarioCorrente.email = usuario.email;
            usuarioCorrente.nome = usuario.nome;
            
            // Salva os dados do usuário corrente no Session Storage, mas antes converte para string
            sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));

            // Retorna true para usuário encontrado
            return true;
        }
    }

    // Se chegou até aqui é por que não encontrou o usuário e retorna falso
    return false;
}

// Apaga os dados do usuário corrente no sessionStorage
function logoutUser () {
    logado = 0;
    localStorage.setItem('logado', JSON.stringify (logado));
    let navlog = document.getElementById("loglink");
    navlog.innerText = "Login";
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}

function addUser (nome, login, senha, email) {
    
    // Cria um objeto de usuario para o novo usuario 
    let newId = generateUUID ();
    let usuario = { "id": newId, "login": login, "senha": senha, "nome": nome, "email": email };
    
    // Inclui o novo usuario no banco de dados baseado em JSON
    db_usuarios.usuarios.push (usuario);

    // Salva o novo banco de dados com o novo usuário no localStorage
    localStorage.setItem('db_usuarios', JSON.stringify (db_usuarios));
}


function comentar(id3) {
    var texto = document.getElementById('mycomment').value;
    var autor = JSON.parse(sessionStorage.getItem('usuarioCorrente')).nome;
    let newId = generateUUID ();
    let comentario = { "id": newId, "id_app": id3, "autor": autor, "texto": texto};
    db_comentarios.comentarios.push (comentario);
    localStorage.setItem('db_comentarios', JSON.stringify (db_comentarios));
    AppPage(id3);
}

//function setUserPass () {

//}

// Inicializa as estruturas utilizadas pelo LoginApp
initLoginApp ();
document.onload = verifyLogged();
