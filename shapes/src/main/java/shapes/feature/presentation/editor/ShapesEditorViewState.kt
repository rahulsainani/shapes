package shapes.feature.presentation.editor

import shapes.feature.domain.ShapeDomainEntity

sealed class ShapesEditorViewState {
    object Empty : ShapesEditorViewState()
    data class Content(val items: List<ShapeDomainEntity>) : ShapesEditorViewState()
}