
Loja XPTO - Projeto Java (Estrutura organizada + single-file)

Conteúdo do ZIP:
- src/main/java/com/loja/model/...   (POJOs)
- src/main/java/com/loja/dao/...     (DAOs + ConnectionFactory)
- src/main/java/com/loja/service/... (Services)
- src/main/java/com/loja/app/MainApp.java  (programa principal de demonstração)
- pom.xml                            (arquivo Maven)
- LojaXPTO_SingleFile.java           (versão monolítica, única para teste rápido)

Como compilar e rodar (modo projeto Maven):
1) Ajuste a string de conexão em ConnectionFactory.java (URL, USER, PASS).
2) No terminal, posicione-se em: /mnt/data/loja_xpto_project
3) Rode: mvn package
4) Rode: java -cp target/loja-xpto-1.0-SNAPSHOT.jar com.loja.app.MainApp
   (ou use mvn exec:java com configuração adequada)

Como compilar e rodar a versão single-file:
1) Ajuste credenciais no começo do arquivo LojaXPTO_SingleFile.java (se necessário).
2) No terminal: javac LojaXPTO_SingleFile.java
3) Rode: java LojaXPTO_SingleFile

Observações:
- Dependência JDBC: o pom.xml inclui mysql-connector-j. O VS Code + Extension Java + Maven deve resolver.
- O código demonstrativo faz operações de leitura/insert; garanta que o schema MySQL do script inicial esteja criado.
- Em produção, adicione tratamento de transações, connection pooling e logs.

