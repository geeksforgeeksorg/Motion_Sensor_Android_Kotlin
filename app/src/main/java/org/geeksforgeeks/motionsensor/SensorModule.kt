package org.geeksforgeeks.motionsensor

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    fun provideMotionSensor(app: Application): MeasurableSensor {
        return MotionSensor(app)
    }
}