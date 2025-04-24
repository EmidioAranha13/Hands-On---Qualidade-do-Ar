package com.example.airqualityapp.utils

data class Slide (
    val title: String = "",
    val image: String = ""
)

val slidePages = listOf(
    Slide(
        "Bem-vindo ao AirQuality!\nO seu sistema de monitoramento de qualidade do ar."),


    Slide(
        "Veja informações detalhadas sobre temperatura, umidade e poluentes."
    ),
    Slide(
        "Use o mapa interativo para explorar diferentes locais!"
    ),
    Slide(
        "Receba alertas quando a qualidade do ar estiver ruim."
    ),
)

val IQA_Range = listOf(
    Triple("Boa", "De 0 à 40", "Qualidade do ar ideal para respiração! Nenhum risco significativo conhecido à saúde. Seguro para todas as pessoas."),
    Triple("Moderada", "De 41 à 80", "Pessoas de grupos sensíveis (crianças, idosos e pessoas com doenças respiratórias e cardíacas) podem apresentar sintomas como tosse seca, espirros e fadiga. A população, em geral, não é afetada."),
    Triple("Ruim","De 81 à 120", "Toda a população pode apresentar sintomas como tosse seca, espirro, cansaço, ardor nos olhos, nariz e garganta. Pessoas de grupos sensíveis (crianças, idosos e pessoas com doenças respiratórias e cardíacas) podem apresentar efeitos mais sérios na saúde como dificuldade para respirar e agravamento de doenças como bronquite e asma."),
    Triple("Péssima", "De 121 à 200", "Toda a população pode apresentar agravamento dos sintomas como tosse seca, cansaço, espirro, ardor nos olhos, nariz, garganta e ainda apresentar sensação de sufocamento, crises asmáticas graves e inflamação no trato respiratório. Exposição prolongada pode levar a doenças pulmonares. Efeitos ainda mais graves à saúde de grupos sensíveis (crianças, idosos e pessoas com doenças respiratórias e cardíacas)."),
    Triple("Crítica", "De 201 e acima", "Toda a população pode apresentar sérios riscos de manifestações de doenças respiratórias e cardiovasculares. Exposição prolongada pode levar a doenças graves, como câncer de pulmão e doenças crônicas. Aumento de mortes prematuras em pessoas de grupos sensíveis."),
)

val heatIndexExtraInfo = listOf(
    Triple(5, "Confortável", "Sem ríscos significativos de saúde."),
    Triple(4, "Moderado", "Cansaço possível após exposição prolongada."),
    Triple(3, "Perigo moderado", "Exaustão pelo calor e cãibras são possíveis."),
    Triple(2, "Perigo Alto", "Golpe de calor provável com exposição prolongada."),
    Triple(1, "Perigo Extremo", "Risco iminente de golpe de calor.")
)

val windChillExtraInfo = listOf(
    Triple(5, "Confortável", "Sem riscos associados ao frio."),
    Triple(4, "Frio leve", "Desconforto possível. Proteja-se ao ar livre."),
    Triple(3, "Frio moderado", "Risco de hipotermia e frostbite com exposição prolongada."),
    Triple(2, "Perigo", "Congelamento em 30 minutos sem proteção."),
    Triple(1, "Perigo Extremo", "Congelamento em até 10 minutos.")
)

val dewPointExtraInfo = listOf(
    Triple(5, "Seco", "Ar confortável, mas pode ressecar pele."),
    Triple(4, "Confortável", "Condições ideais para a maioria das pessoas."),
    Triple(3, "Ligeiramente Úmido", "Começa a parecer abafado."),
    Triple(2, "Desconfortável", "Suor evapora com dificuldade."),
    Triple(1, "Muito Desconfortável", "Extremamente abafado. Risco de superaquecimento.")
)

val pm25ExtraInfo = listOf(
    Triple(5, "Bom", "Poeira fina, gotículas d'água, partículas naturais de solo e mar, emissões de vegetação."),
    Triple(4, "Moderado", "Fumaça leve de queima de madeira ou cigarro, emissões de carros e caminhões, poluição urbana."),
    Triple(3, "Ruim","Queima de combustível em indústrias e veículos, incêndios florestais, poluição intensa."),
    Triple(2, "Péssimo", "Neblina tóxica, poluição densa, áreas próximas a incêndios ou fábricas poluentes."),
    Triple(1, "Critico", "Cenários de alto risco, como grandes queimadas, tempestades de poeira e regiões industriais com poluição extrema.")

)

val pm10ExtraInfo = listOf(
    Triple(5, "Bom", "Pólen de plantas, poeira leve, partículas de solo."),
    Triple(4, "Moderado", "Poeira de estradas não pavimentadas, obras, pequenas tempestades de areia."),
    Triple(3, "Ruim", "Poeira intensa, cinzas vulcânicas leves, emissões de fábricas, fumaça de queimadas."),
    Triple(2, "Péssimo", "Poeira pesada de construção, tempestades de areia, grandes incêndios."),
    Triple(1, "Crítico", " Tempestade Fortes de detrítos como terra e areia, cidades extremamente poluídas, áreas próximas a queimadas e desastres ambientais.")
)

val coExtraInfo = listOf(
    Triple(5, "Aceitável", "Concentração segura de monóxido de carbono. Nenhum risco à saúde."),
    Triple(4, "Em Alerta", "Pequeno aumento na concentração de CO. Pessoas mais sensíveis podem sentir leves sintomas como fadiga."),
    Triple(3, "Em Rísco", "Níveis de CO começam a impactar a saúde. Pode causar dores de cabeça e tontura em exposições prolongadas."),
    Triple(2, "Perígo", "Exposição pode causar sintomas graves, como náuseas e confusão mental. É necessário ventilação e precaução."),
    Triple(1, "Périgo Extremo", "Concentração perigosa de CO. Pode levar a intoxicação severa e risco de morte em exposições prolongadas. Ventilar o ambiente imediatamente.")
)

val faqAnswers = listOf(
    "    O AirQuality é um aplicativo que monitora e exibe informações sobre a qualidade do ar em tempo real.",
    "    Veja informações detalhadas sobre temperatura, umidade e possíveis poluentes no ar que podem causar problemas respiratórios ou asfixia.",
    "    Use o mapa interativo para explorar diferentes locais! Visualize ou inclua pontos em regiões que apresentem níveis de poluição variádos.",
    "Receba alertas quando a qualidade do ar estiver ruim.",
    "    Na tela principal, são exibidas as leituras dos sensores disponíveis na estação, incluindo informações sobre partículas presentes no ar, temperatura, umidade e a presença de gases tóxicos e inflamáveis.",
    "    Logo no início, são exibidos a região geográfica, o horário atualizado e o índice de qualidade do ar do ambiente (IQA), calculado com base nas leituras dos sensores, junto com o status da qualidade do ar. Abaixo dessas informações, há um resumo detalhado da qualidade do ar, que pode ser expandido ou ocultado conforme desejado.",
    "    O resumo detalhado da qualidade do ar segue um padrão de indetificação fornecido pela OMS - Organização Mundial de Saúde. Esta qualificação do ar está vinculada à norma legal (Resolução CONAMA n° 491/2018). Segue abaixo as possíveis classificações de qualidade do ar:",
    "    Na tela principal, também há cartões informativos específicos para cada sensor e medição disponível, que podem ser expandidos para exibir mais dados relacionados.",
    "    Sempre que um cartão apresentar uma mudança significativa em suas medições, um ícone de alerta será exibido no canto superior direito. Isso facilita a identificação da possível causa do aumento no IQA. Basta observar os dados em \"Mostrar mais\" para mais esclarecimentos",
    "    Para alternar entre a tela principal, o mapa e a área de dúvidas, basta utilizar o menu de seleção localizado no topo da tela.",
    "    O sensor DHT11 é responsável por medir a temperatura e a umidade. As medições são feitas em graus Celsius e porcentagem, respectivamente.",
    "    Ao expandir o cartão do DHT11, dependendo das condições de temperatura e umidade, será possível visualizar informações específicas, como sensação térmica, ponto de orvalho e resfriamento evaporativo. Além disso, serão exibidas suas medições em graus Celsius, o status correspondente à faixa de cálculo e uma breve descrição dos riscos associados a essas condições.",
    "    O sensor SDS011 é responsável por detectar partículas de poluentes presentes no ar. Ele mede partículas finas com diâmetro de até 2,5 micrômetros/µm. (PM2.5) e grossas com diâmentro de até 10 micrômetros/µm (PM10), sendo que valores menores indicam uma melhor qualidade do ar. A leitura de sua medição é feita em microgramas por metro cúbico (µg/m³).",
    "    Ao expandir o cartão do sensor SDS011, serão exibidos detalhes de classificação e fatores específicos para os valores de PM2.5 e PM10, independentemente do cálculo do IQA. Dessa forma, facilita-se o entendimento do que estão afetando as medições. Geralmente, o PM2.5 é considerado mais perigoso para a saúde, pois afeta diretamente os pulmões e o sistema cardiovascular.",
    "    O MQ-9 é um sensor que detecta monóxido de carbono (CO), GLP e gases combustíveis (metano, propano, butano). Ele utiliza um elemento sensor de óxido de estanho (SnO₂), cuja resistência elétrica varia conforme a presença desses gases. Quanto menor o valor, melhor a qualidade do ar.",
    "O MQ-9 opera em dois modos de temperatura, alternando para detectar diferentes gases:",
    "   * Alta temperatura (~300-500°C). Prioriza CO."+"\n"+"   * Baixa temperatura (~100-200°C). Prioriza GLP e gases combustíveis.",
    "OBS: As temperaturas indicadas referem-se ao interior do sensor e não estão relacionadas à temperatura ambiente.",
    "    Ao expandir o cartão do sensor MQ-9, serão exibidos o status do CO ou de gases inflamáveis com base no índice de concentração determinado pela OMS, além dos possíveis fatores que podem estar causando essa concentração.",
    "    A seção de mapa exibe medições do IQA feitas por dispositivos com o AirQuality, conectados ao servidor base da aplicação, na sua cidade. Ela serve como um alerta geral sobre a qualidade do ar nas regiões de interesse, permitindo um preparo antecipado ou servindo apenas para informação.",
    "    Você pode contribuir adicionando um ponto ao mapa com as medições feitas pelo seu dispositivo na sua localização atual. Assim, suas medições serão enviadas para a base e compartilhadas com os demais."
)