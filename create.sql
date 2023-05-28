CREATE TABLE medicamentos(
    codigo TEXT
    quantidade INT
    pesoEmGramas INT
    statusGenerico BOOLEAN
    statusTarjaPreta BOOLEAN
    nome TEXT
    fabricante TEXT
    outrasInformacoes TEXT
    cnpjFornecedor TEXT
);
CREATE TABLE enfermeiros (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  cpf VARCHAR(14) UNIQUE NOT NULL,
  rg VARCHAR(12) UNIQUE NOT NULL,
  telefone VARCHAR(15) NOT NULL,
  lotacao VARCHAR(50) NOT NULL
);