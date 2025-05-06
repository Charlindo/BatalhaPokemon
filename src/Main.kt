// Definindo as classes para os Pokémons e Jogadores
data class Pokemon(val nome: String, val tipo: String, val poder: Int)

class Jogador(val nickname: String) {
    val time: MutableList<Pokemon> = mutableListOf()

    fun adicionarPokemon(pokemon: Pokemon) {
        if (time.size < 6) {
            time.add(pokemon)
            println("${pokemon.nome} foi adicionado ao seu time!")
        } else {
            println("Você já tem 6 pokémons no seu time! Não é possível adicionar mais.")
        }
    }

    fun escolherPokemon(): Pokemon {
        println("${nickname}, escolha um Pokémon para a batalha:")
        time.forEachIndexed { index, pokemon ->
            println("${index + 1}. ${pokemon.nome} (Poder: ${pokemon.poder})")
        }

        val escolha = readLine()?.toIntOrNull() ?: 0
        return if (escolha in 1..time.size) {
            time[escolha - 1]
        } else {
            println("Escolha inválida, usando o primeiro Pokémon.")
            time[0]
        }
    }
}

fun batalha(jogador1: Jogador, jogador2: Jogador) {
    println("\nA batalha começa entre ${jogador1.nickname} e ${jogador2.nickname}!")

    val pokemon1 = jogador1.escolherPokemon()
    val pokemon2 = jogador2.escolherPokemon()

    println("\nPokémons escolhidos: ${pokemon1.nome} (Poder: ${pokemon1.poder}) vs ${pokemon2.nome} (Poder: ${pokemon2.poder})")

    when {
        pokemon1.poder > pokemon2.poder -> println("\n${jogador1.nickname} venceu a batalha!")
        pokemon1.poder < pokemon2.poder -> println("\n${jogador2.nickname} venceu a batalha!")
        else -> println("\nA batalha terminou em empate!")
    }
}

fun main() {
    println("Bem-vindo ao sistema de batalha Pokémon!")

    // Criando Jogadores
    println("\nJogador 1, digite seu nickname:")
    val jogador1 = Jogador(readLine() ?: "Jogador1")

    println("\nJogador 2, digite seu nickname:")
    val jogador2 = Jogador(readLine() ?: "Jogador2")

    // Adicionando Pokémons ao time de cada jogador
    for (i in 1..6) {
        println("\nJogador ${jogador1.nickname}, adicione o Pokémon #$i ao seu time:")
        print("Nome: ")
        val nome = readLine() ?: "Pokémon$i"
        print("Tipo: ")
        val tipo = readLine() ?: "Tipo$i"
        print("Poder (0 a 100): ")
        val poder = readLine()?.toIntOrNull() ?: 0
        jogador1.adicionarPokemon(Pokemon(nome, tipo, poder))

        println("\nJogador ${jogador2.nickname}, adicione o Pokémon #$i ao seu time:")
        print("Nome: ")
        val nome2 = readLine() ?: "Pokémon$i"
        print("Tipo: ")
        val tipo2 = readLine() ?: "Tipo$i"
        print("Poder (0 a 100): ")
        val poder2 = readLine()?.toIntOrNull() ?: 0
        jogador2.adicionarPokemon(Pokemon(nome2, tipo2, poder2))
    }

    // Realizando a batalha
    batalha(jogador1, jogador2)
}
