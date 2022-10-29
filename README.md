<div>
  <img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/3136b8804702be3dd38a70b81eded0c661dd5a91/Imagens/araujo.png" width="100px"> 
  <img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/dfe26a62f0d311397f2d359aca91c5cfa08b29c4/Imagens/Gama.png" width="100px" align=right>
</div>

<h1 align="center"><font color="#0000FF">X</font><font color="#000099">Market</font></h1>

<p align="center">

A empresa XMarket lançou um novo serviço de vendas online de produtos e deseja gerar relatórios de testes de unidade relacionados a parte de cadastro de Clientes.
O desafio consiste em construir na linguagem Java a parte operacional da aplicação . Após isso, a equipe deverá executar, no mínimo, dois testes estruturais demonstrando a estabilidade, segurança e confiabilidade desse cadastro.

✨ Sistema
===============

O sistema foi desenvolvido em Java e seus componentes gráficos criados através do GUI WindowBuilder, um banco de dados relacional foi criado através do MySQL. Os relatórios foram todos desenvolvidos através do Jasper Reports.

🏗️ Desenvolvedores
=================

- [x] André Augusto Xavier de Miranda
- [x] Agnaldo Machado
- [x] Cristiano Daniel Nascimento de Melo 
- [x] Weslley J. Leles

Manual do Usuário: 
https://drive.google.com/file/d/1Do23JUCu7TydSuCpNaSqobHPlCttbyfi/view?usp=sharing

📝 Features
=====================
* Login
* Clientes CRUD
* Produtos CRUD
* Marcas CRUD
* Pedidos CRUD
* Integração com API dos correios para autopreenchimento do CEP.
* Validação de CPF.

🚀 Tecnologias
=================

<table>
<tr>
<td>Java 17</td>
<td>Eclipse IDE</td>
<td>Window Builder</td>
<td>MySQL Server</td>
<td>Jasper Reports</td>
</tr>
</table>

## Projeto Local

Para executar o projeto localmente, você precisará do Git, Eclipse ( ou outra IDE compatível) e o MySQL instalado em seu computador.

Acesse seu MySQL e crie um database com o seguinte Script:

https://drive.google.com/file/d/1G-kym_S7yMAz70DYHaDiwgAL2w_uupC7/view?usp=sharing

Depois de clonar o projeto, será necessário editar o arquivo de conexão com o banco de dados, no caminho ../MenuJdesktop/src/Conexao/Dao/ConectaBancoDao.java e inserir os dados de conexão ( caminho do servidor na linha 19, usuário na linha 20 e senha na linha 21)

```bash
# Clone este repositório
$ https://github.com/Cristiano-Melo/ProjetoXMarket
```


📇 Manual do Usuário
=================

#### Login

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/e2857cec8366803c5fb991814ea01ce1985d695e/Imagens/Login.png">
Ao executar o atalho para acesso ao sistema, será apresentada a tela, conforme imagem acima. Para acesso é necessário ter um usuário e senha previamente registrados no sistema. <br><br>

#### Etapas do Sistema

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/57839bf4f9b2d3c9e147e347b88103f2d0bc8cf0/Imagens/TelaInicial.png">

Assim que usuário e senha forem validados será apresentada esta tela com todas as opções disponíveis no sistema. Basta selecionar a opção e executar cada uma das operações conforme relatado a seguir.

#### Marcas

Menu: Processsos, Marca

Esta opção faz manutenção no cadastro das Marcas. Ter uma Marca cadastrada é pré requisto para fazer o cadastro de um produto no sistema. 

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/57839bf4f9b2d3c9e147e347b88103f2d0bc8cf0/Imagens/Marca.jpeg">

#### Clientes

Opção destinada à manutenção na base de clientes utilizada nas rotinas do sistema. O cadastro de clientes é básico para o processo de venda, assim como para emissão do relatório de clientes e de vendas.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/57839bf4f9b2d3c9e147e347b88103f2d0bc8cf0/Imagens/Clientes.png">


<b>Funcionalidade dos botões</b>

<b>Gravar: </b> o acionamento deste botão faz a validação dos dados inseridos nos campos existentes na tela, e estando de acordo com os critérios definidos o cliente é inserido na base de dados. Após esta inserção o novo cadastro é apresentado no grid da tela, demonstrando a inclusão do mesmo no sistema.

<b> Alterar: </b> realiza a alteração no cadastro do cliente. É necessário selecionar o cliente a ser alterado no grid da tela, para então realizar as alterações no cadastro. As mesmas validações realizadas para o cadastro são realizadas para a alteração. Após confirmação da alteração o registro é apresentado no grid da tela apresentando as alterações realizadas.

<b> Deletar: </b> realiza a exclusão do cadastro da base de dados do sistema. É necessário selecionar o cliente a ser excluído no grid da tela, para então realizar a exclusão do mesmo.

<b> Limpar: </b> faz a limpeza de todas as informações apresentadas na tela. 

<b> Lupa Pesquisa: </b> ao clicar na Lupa será aberta uma tela para seleção do tipo de consulta, se todos os clientes ou pesquisa por Nome, CPF ou se Todos os clientes. com algum trecho do nome a pesquisa é realizada. Em todos os casos os registros são apresentados no grid da tela.


<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/d4b06e9b4c298c15caebe0c1386519d3dd506c6d/Imagens/Clientes%20Table.png">


#### Produtos

Opção destinada à manutenção do cadastro de produtos controlados pelo sistema. O cadastro dos produtos é básico para emissão do comprovante de vendas e relatórios de estoque e vendas.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/d4b06e9b4c298c15caebe0c1386519d3dd506c6d/Imagens/Produto.jpeg">

<b>Funcionalidade dos botões</b>

<b>Gravar:</b> o acionamento deste botão faz a validação dos dados inseridos nos campos existentes na tela, e estando de acordo com os critérios definidos o produto é inserido na base de dados. Após esta inserção o novo cadastro é apresentado no grid da tela, demonstrando a inclusão do mesmo no sistema.

<b>Alterar:</b> realiza a alteração no cadastro do produto. É necessário selecionar o cliente a ser alterado no grid da tela, para então realizar as alterações no cadastro. As mesmas validações realizadas para o cadastro são realizadas para a alteração. Após confirmação da alteração o registro é apresentado no grid da tela apresentando as alterações realizadas.

<b>Deletar:</b>  realiza a exclusão do cadastro da base de dados do sistema. É necessário selecionar o produto a ser excluído no grid da tela, para então realizar a exclusão do mesmo.

<b>Limpar:</b> faz a limpeza de todas as informações apresentadas na tela.

<b>Lupa Pesquisa:</b> ao clicar na Lupa será aberta uma tela para seleção do tipo de consulta, se todos os produtos ou pesquisa por Nome, com algum trecho do nome a pesquisa é realizada. Em ambos os casos os registros são apresentados no grid da tela.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/07786985195c3502afa2e5d69cc8bc7d41ce0412/Imagens/Produtos%20Table.png">

#### Pedidos

Opção para confecção de orçamentos e pedidos de venda. É possível gerar um Orçamento ou um Pedido. Sendo que:  o orçamento pode ser gerado como uma “pesquisa” para posteriormente ser transformado em Pedido. Enquanto Orçamento este pode sofrer manutenção em qualquer informação passível de manutenção, como por exemplo: confição de pagamento, cliente, produtos, etc. Para efetuar esta manutenção é necessário selecionar a opção Orçamento e então será apresentao um Combo Box com os orçamentos existentes, basta selecionar um deles para manutenção e/ou transforma-lo em um pedido, gerando assim a venda.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/07786985195c3502afa2e5d69cc8bc7d41ce0412/Imagens/Pedido.jpeg">

<b>Funcionalidade dos botões</b>

<b>Excluir Orçamento:</b> este botão só estará visível na tela quando a opção de Orçamento estiver selecionada e existir orçamento 'em aberto'. Dai ao ser acionado o orçamento apresentado é excluído da base de dados.

“...”: através deste botão é feita a seleção do cliente para o Orçamento/Pedido

<b>Pesquisar Produtos:</b> realiza a pesquisa dos produtos por qualquer parte do nome. Os produtos que atendem aos critérios da pesquisa são apresentados no grid superior da tela para que então possam ser selecionados para a composição do orçamento/pedido.

<b>“+”:</b> Ao clicar em determinado produto no grid, na parte superior da tela, o produto é selecionado; dai basta informar a quantidade desejada no campo “Quantidade”, e o acionamento deste botão inclui o produto no grid na parte inferior da tela.

<b>“-”:</b> Ao posicionar em determinado produto no grid, na parte inferior da tela, o produto é selecionado; dai basta acionar este  que o produto será excluído do grid da parte inferior da tela.

<b>Gravar:</b> faz o registro no sistema do orçamento/pedido que foi montado na tela.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/0f04410e4b90b9a4e5e706e2317fb481ee0f5ffe/Imagens/Pedidos%20Table.png">

<b>Limpar:</b> faz a limpeza de todas as informações apresentadas na tela.

#### Consultas

Opção para consultas em telas dos temas,  conforme imagem abaixo, onde apresenta as consultas disponíveis. Elas possuem filtro atendendo à condição de cada uma.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/a655465be6ce207a2f5346552551d39c8ed93f3c/Imagens/Tela%20Consultas.png">


#### Relatórios

Opção para geração de consulta impressa. São diversas opções para atender a cada necessidade de forma específica.Todas elas possuem filtros que atendem às diversas necessidades.

<img src="https://github.com/Cristiano-Melo/ProjetoXMarket/blob/a655465be6ce207a2f5346552551d39c8ed93f3c/Imagens/Tela%20Relatorios.png">


Desenvolvido por: <br>

🔹 André Augusto Xavier de Miranda 👋 [Get in touch](https://github.com/MIR4NDINH4)<br>
🔹 Agnaldo Machado 👋 [Get in touch](https://github.com/AgnaldoMachado)<br>
🔹 Cristiano Daniel Nascimento de Melo 👋 [Get in touch](https://github.com/Cristiano-Melo)<br>
🔹 Weslley J. Leles 👋 [Get in touch](https://github.com/weslleyjleles)<br>
