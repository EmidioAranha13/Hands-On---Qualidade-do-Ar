# Air Quality Lib

A blioteca do Air Quality segue o padrão de projeto singleton, dessa forma uma mesma instância é reaproveitada para leitura dos valores dos sensores na hal. Além disso, os dados são salvos em uma memórica cache que é atualizada quando uma leitura é maior que 5 segundos desde a última leitura, evitando muitas leituras no arquivo do driver.

Para aplicar a lib, copie este diretório e cole em:

```
inserir comando cp aqui.
```

Após isso, basta compilar junto com o projeto.
