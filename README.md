# CodeHero

Aplicativo Android que mostra as informações dos personagens da marvel coletadas na API
.
## Arquitetura
Foi utilizado arquitetura reativa (redux) que funciona separando o código em middlewares e reducers, bem como atualizando o layout a partir das alterações ocorridas no App State.
Os principais componentes dessa arquitetura são:
### Action Creator + Dispatcher:

Cada mudança ou interação do usuário com o app tem uma ação, o dispatcher é responsável por ordenar as ações e encaminhado-las ao middleware ou reducer.
### Middleware:

Responsável por todos dados assíncronos, nesse projeto tem o objetivo de sincronizar os dados com o servidor e salvar os dados no banco de dados. 
### Reducer:

Responsável por manter sempre atualizado os dados no App State, que é utilizado nas telas para mostrar as informações para o usuário.
### Listener:

Responsável por atualizar a tela caso ocorra alguma alteração no App State.
## Bibliotecas utilizadas

#### [Redukt](https://github.com/raulccabreu/redukt)
Biblioteca escrita em Kotlin para arquitetura redux.

#### [Retrofit](https://square.github.io/retrofit/)
HTTP Client para Android

#### [Gson](https://github.com/google/gson)
Biblioteca para converter Json para Kotlin e vice versa

#### [Anvil](http://trikita.co/anvil/)
Biblioteca para desenvolver layouts e views reativos para Android
