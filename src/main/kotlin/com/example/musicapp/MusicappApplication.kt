package com.example.musicapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.annotation.Id

@SpringBootApplication
class MusicappApplication

fun main(args: Array<String>) {
    runApplication<MusicappApplication>(*args)
}


