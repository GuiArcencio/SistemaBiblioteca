drop database if exists Biblioteca;

create database Biblioteca;

use Biblioteca;

CREATE TABLE editora(
    id bigint not NULL auto_increment,
    nome varchar(100) not NULL,
    CONSTRAINT editora_pk PRIMARY KEY(id)
);

CREATE TABLE categoriaobra(
    codigo bigint not NULL auto_increment,
    descricao varchar(256),
    maximoDiasEmprestimo int not null,
    taxaMulta decimal(10,6) not NULL,
    CONSTRAINT codigo_pk PRIMARY KEY (codigo)
);

CREATE TABLE obra(
    codigo bigint not NULL auto_increment,
    isbn bigint not NULL,
    titulo varchar(256) not NULL,
    categoriaObra_id bigint not NULL,
    palavrasChave varchar(256),
    dataPublicacao date not NULL,
    edicao varchar(50) not NULL,
    editora_id bigint not NULL,
    numPaginas int not NULL,
    status varchar(12) not NULL,
    CONSTRAINT obra_check CHECK (status IN('DISPONIVEL', 'INDISPONIVEL')),
    CONSTRAINT obra_pk PRIMARY KEY (codigo),
    CONSTRAINT categoriaObra_id_fk FOREIGN kEY(categoriaObra_id) REFERENCES categoriaobra(codigo),
    CONSTRAINT editora_id_fk FOREIGN KEY(editora_id) REFERENCES editora(id)
);


CREATE TABLE autor(
    codigo bigint not NULL auto_increment,
    nome varchar(256) not NULL,
    iniciais varchar(30),
    CONSTRAINT autor_pk PRIMARY KEY(codigo)
);


CREATE TABLE relobraautor(
    id bigint not NULL auto_increment,
    codigo_autor bigint not NULL,
    codigo_obra bigint not NULL,
    CONSTRAINT obraAutor_pk PRIMARY KEY(id),
    CONSTRAINT relAutor_fk FOREIGN KEY (codigo_autor) REFERENCES autor(codigo),
    CONSTRAINT relObra_fk FOREIGN KEY (codigo_obra) REFERENCES obra(codigo)
);

CREATE TABLE copia(
    id bigint not NULL auto_increment,
    estado varchar(50) not NULL,
    obra_id bigint not NULL,
    CONSTRAINT copia_pk PRIMARY KEY(id),
    CONSTRAINT copia_fk FOREIGN KEY(obra_id) REFERENCES obra(codigo)
);

CREATE TABLE reserva(
    id bigint not NULL auto_increment,
    dataReserva date not NULL,
    dataPrevistaRetirada date not NULL,
    dataPrevistaDevolucao date not NULL,
    funcionarioResponsavel bigint not NULL,
    leitor bigint not NULL,
    copiaReservada bigint not NULL,
    CONSTRAINT reserva_pk PRIMARY KEY(id)
);

CREATE TABLE endereco(
    id bigint not NULL auto_increment,
    logradouro varchar(50) not NULL,
    numero int not NULL,
    cep int not NULL,
    cidade varchar(20),
    estado varchar(20),
    CONSTRAINT endereco_pk PRIMARY KEY(id)
);

CREATE TABLE usuario(
    id bigint not NULL auto_increment,
    nome varchar(50) not NULL,
    telefone varchar(20) not NULL,
    dataNascimento date not null,
    endereco_id bigint,
    role varchar(20) not NULL,
    CONSTRAINT usuario_pk PRIMARY KEY(id),
    CONSTRAINT endereco_fk FOREIGN KEY(endereco_id) REFERENCES endereco(id)
);

CREATE TABLE categorialeitor(
    id bigint not NULL auto_increment,
    maximoDiasEmprestimo int not NULL,
    descricao varchar(50),
    CONSTRAINT categoriaLeitor_pk PRIMARY KEY(id)
);

CREATE TABLE leitor(
    idUsuario bigint not null,
    email varchar(50) not NULL,
    documentoId bigint not null,
    categoria_id bigint,
    grupoAcademico boolean not NULL,
    CONSTRAINT leitor_fk FOREIGN KEY (idUsuario) REFERENCES usuario(id),
    CONSTRAINT categoia_fk FOREIGN KEY (categoria_id) REFERENCES categorialeitor(id),
    CONSTRAINT leitor_pk PRIMARY KEY(idUsuario)
);

CREATE TABLE emprestimo(
    id bigint not NULL auto_increment,
    dataEmprestimo date not NULL,
    dataPrevistaDevolucao date not NULL,
    funcionarioResponsavel bigint not NULL,
    leitor bigint not NULL,
    codigoCopia bigint not NULL,
    atrasado boolean not NULL,
    CONSTRAINT emprestimo_pk PRIMARY KEY(id),   
    CONSTRAINT funcionario_fk FOREIGN KEY (funcionarioResponsavel) REFERENCES usuario(id),
    CONSTRAINT emprestimo_leitor_fk FOREIGN KEY (leitor) REFERENCES leitor(idUsuario),
    CONSTRAINT emprestimo_copia_fk FOREIGN KEY (codigoCopia) REFERENCES copia(id)
);

CREATE TABLE devolucao(
    id bigint not NULL auto_increment,
    dataDevolucao date not NULL,
    multaTotal decimal(10,6) not NULL,
    codigo_emprestimo bigint not NULL,
    CONSTRAINT devolucao_fk FOREIGN KEY(codigo_emprestimo) REFERENCES emprestimo(id),
    CONSTRAINT devolucao_pk PRIMARY KEY(id)
);

CREATE TABLE pessoainteressada(
    id bigint not null auto_increment,
    status varchar(12) default null,
    leitorId bigint not null,
    obraCodigo bigint not null,
    CONSTRAINT pi_pk PRIMARY KEY(id),
    CONSTRAINT pi_li_fk FOREIGN KEY(leitorId) REFERENCES leitor(idUsuario),
    CONSTRAINT pi_oc_fk FOREIGN KEY(obraCodigo) REFERENCES obra(codigo)
);
