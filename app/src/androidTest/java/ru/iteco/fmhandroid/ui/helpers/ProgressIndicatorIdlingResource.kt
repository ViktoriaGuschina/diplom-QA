package ru.iteco.fmhandroid.ui.helpers

import android.app.Activity
import android.view.View
import androidx.test.espresso.IdlingResource
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

class ProgressIndicatorIdlingResource(
    private val progressIndicatorId: Int
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null
    private var isIdle: Boolean = false

    override fun getName(): String {
        return "ProgressIndicatorIdlingResource:$progressIndicatorId"
    }

    override fun isIdleNow(): Boolean {
        if (isIdle) return true

        // Получаем текущую активность
        val activity = getCurrentActivity() ?: return false

        // Ищем индикатор прогресса
        val progressIndicator = activity.findViewById<View>(progressIndicatorId)

        // Проверяем, видим ли индикатор
        val isVisible = progressIndicator != null &&
                progressIndicator.visibility == View.VISIBLE &&
                progressIndicator.isShown

        // Если индикатор не виден, переходим в состояние idle
        if (!isVisible) {
            isIdle = true
            resourceCallback?.onTransitionToIdle()
        }

        return !isVisible
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.resourceCallback = callback
    }

    private fun getCurrentActivity(): Activity? {
        // Реализация получения текущей активности
        // Зависит от вашей архитектуры приложения
        try {
            return ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED).firstOrNull()
        } catch (e: Exception) {
            return null
        }
    }
}