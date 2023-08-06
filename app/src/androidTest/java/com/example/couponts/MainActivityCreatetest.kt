package com.example.couponts

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.couponts.ui.home.view.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest //esta anotación es usada para tareas que puedan demorar su ejecución
@RunWith(AndroidJUnit4::class) //con esta anotación podemos indicarle cual es el núcleo que hará funcinar estas pruebas
class MainActivityCreatetest {

    @get:Rule //las reglas son una forma de importar código reutilizable dentro del testing
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createCouponTest(){
        //el método onView es aquel que nos va a permitir acceder a una vista  en tiempo real
        //requerimos una forma d apuntar al componente espedifico y eso lo hacemos con
        val edcoupon = onView(withId(R.id.edCoupon))
        edcoupon.check(matches(withText("")))// verifica que (view)(coincida(con el texto("")))
        edcoupon.perform(click())
        edcoupon.perform(replaceText("Hola04"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId((R.id.btnCreate)))
        btnCreate.check(matches(isDisplayed()))

        val edDescription = onView(withId((R.id.edDescription)))
        edDescription.perform(replaceText("2 x 1 e sodas."))

        btnCreate.perform(click())

        val snackbar = onView(withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(matches(withText("Cupón creado")))
    }

    /*
    corroborar que el botón no es visible.
    Test: nuestro edcoupon inicia vacio,luego haz click sobre el, añade el texto "Hola03"
    ahora sobre el btnConsult haz click sobre el,
    vefifica que el btnCrear no es visible
     */

    fun consultCouponExistTest(){
        val edcoupon = onView(withId(R.id.edCoupon))
        edcoupon.check(matches(withText("")))
        edcoupon.perform(click())
        edcoupon.perform(replaceText("Hola03"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(not(isDisplayed())))
        val edDescription = onView(withId((R.id.edDescription)))
        edDescription.perform(replaceText("3 x 2 en chocolates."))

        btnCreate.perform(click())

        val snackbar = onView(withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(matches(withText("Este cupón ya existe, agregueun código diferente")))

    }

/*
* comprueba que no se puede crear un cupón con código repetido
*  Test: edcoupon inica vacio y se remplaza textocon un código existente
* corrobora que nuestro boton btnCreate, existe
* despúes añade descripción y edita editex coupon(pon un códigg existente)
* click en nuestro btnCreate
* comprueba el mensaje de snackbar.
* */
    @Test
    fun createCouponwithOldCodeTest(){
        val edcoupon = onView(withId(R.id.edCoupon))
        edcoupon.perform(replaceText("Hola03"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(not(isDisplayed())))
    }
}