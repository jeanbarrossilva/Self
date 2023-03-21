package com.jeanbarrossilva.self.feature.wheel.domain

import java.io.Serializable

internal data class FeatureArea(
    val name: String,
    val attention: Float,
    val toDos: List<FeatureToDo>
) : Serializable {
    companion object {
        @Suppress("SpellCheckingInspection")
        val samples = listOf(
            FeatureArea(
                name = "Família",
                attention = .3f,
                toDos = listOf(
                    FeatureToDo(title = "Brincar com a Helena", isDone = true),
                    FeatureToDo(title = "Levar o João ao clube", isDone = false),
                    FeatureToDo(title = "Ir a um restaurante", isDone = false)
                )
            ),
            FeatureArea(
                name = "Trabalho",
                attention = .9f,
                toDos = listOf(
                    FeatureToDo(title = "Entregar o relatório desse trimestre", isDone = false),
                    FeatureToDo(title = "Conversar com o Ali sobre sua performance", isDone = true),
                    FeatureToDo(title = "Corrigir o bug na seção de compras do app", isDone = true),
                    FeatureToDo(title = "Remarcar a reunião de segunda-feira", isDone = true)
                )
            ),
            FeatureArea(
                name = "Lazer",
                attention = .1f,
                toDos = listOf(
                    FeatureToDo(title = "Caminhar pelo Jardim Botânico", isDone = false),
                    FeatureToDo(title = "Conhecer a Pompeia", isDone = false),
                    FeatureToDo(title = "Consertar o piano", isDone = true),
                    FeatureToDo(title = "Jogar com o Bernardo", isDone = false)
                )
            ),
            FeatureArea(
                name = "Estudos",
                attention = .5f,
                toDos = listOf(
                    FeatureToDo(title = "Estudar sobre a Idade Média", isDone = false),
                    FeatureToDo(title = "Entregar a redação", isDone = false),
                    FeatureToDo(title = "Fazer o módulo 2 do livro", isDone = false),
                    FeatureToDo(title = "Estudar a matéria do próximo bimestre", isDone = true)
                )
            )
        )

        val sample = samples.first()
    }
}
