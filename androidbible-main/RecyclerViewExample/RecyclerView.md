# RecyclerView
Para representar listas en Android hay dos tipos de componentes, los ListView y los RecyclerView. Con la aparición de los segundos se consigue un listado mucho más óptimo, pues los elementos se van dibujando (reciclando) según se van necesitando, mientras que con ListView se cargan todos desde el principio.

Para representar este tipo de listas, necesitamos implementar principalmente 2 tipos de clases:
- **Adapter**: Se encargará de decir a la lista qué información tiene que pintar a partir de una fuente de datos
- **ViewHolder**: Se encargará de decir a cada celda de la lista el contenido que tiene que dibujar

Para explicar este tipo de listados vamos a realizar un listado de superhéroes (Fuente: [Tutorial RECYCLERVIEW DEFINITIVO en ANDROID 📱 con KOTLIN en español + GRID [Android en Kotlin 2022]](https://www.youtube.com/watch?v=k3zoVAMuW5w))

Para este ejemplo se ha usado el ViewBinding, opción que se puede activar en el fichero gradle del módulo de aplicación, incluyendo lo siguiente:

```gradle
android {
	...
	 viewBinding {
        enabled = true
    }
    ...
}

```

## Definiendo clase SuperHero
Definiremos una data class como la siguiente
```kotlin
data class SuperHero(
    var name: String,
    val realName: String,
    val publisher: Publisher,
    @DrawableRes val photo: Int,
)

sealed class Publisher(
    val publisherName: String
) {
    object Marvel : Publisher("Marvel")
    object Dc : Publisher("DC")
    object Image : Publisher("Image comics")
}

fun Publisher.publisherColor() =
    when (this) {
        Publisher.Marvel -> Color.RED
        Publisher.Dc -> Color.BLUE
        Publisher.Image -> Color.GREEN
    }
```

- SuperHero: Representa el tipo de objeto que se va a dibujar en la lista
- Publisher: Representa los tipos de editoriales a los que puede pertenecer un superhéroe. Está representado como una sealed class por buenas prácticas y por comodidad, ya que comparar, agrupar y operaciones similares por String es más tedioso.
- Función de extensión *publisherColor()*: Lo usaremos más adelante en nuestro *ViewHolder*

## Maquetando Activity
El layout del Activity tendrá únicamente un recyclerview y un botón que usaremos para más adelante
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/superhero_list"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:padding="8dp"
        app:layout_constraintBottom_toTopOf="@id/add_superhero"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:listitem="@layout/view_holder_superhero" />

    <Button
        android:id="@+id/add_superhero"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Add Superhero"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/superhero_list" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Maquetando ViewHolder - Celda - de superhéroe
Nuestra celda tendrá lo siguiente:

- ImageView para representar el avatar del superhéroe
- TextView para representar nombre, nombre real y publisher
- Button para eliminar y editar

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/superhero_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="12dp">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/avatar"
            android:layout_width="150dp"
            android:layout_height="150dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:src="@drawable/ic_launcher_background" />

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/info_container"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:padding="24dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toStartOf="@id/action_container"
            app:layout_constraintStart_toEndOf="@+id/avatar"
            app:layout_constraintTop_toTopOf="parent">

            <TextView
                android:id="@+id/name"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:textColor="@color/black"
                android:textSize="24sp"
                android:textStyle="bold"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                tools:text="Spiderman" />

            <TextView
                android:id="@+id/real_name"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:padding="12dp"
                android:textSize="12sp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/name"
                tools:text="Peter Parker" />

            <TextView
                android:id="@+id/publisher"
                android:layout_width="0dp"
                android:layout_height="0dp"
                android:gravity="bottom|end"
                android:textAllCaps="true"
                android:textSize="12sp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@id/real_name"
                tools:text="Marvel" />


        </androidx.constraintlayout.widget.ConstraintLayout>

        <LinearLayout
            android:id="@+id/action_container"
            android:layout_width="wrap_content"
            android:layout_height="0dp"
            android:orientation="vertical"
            android:padding="24dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent">

            <ImageView
                android:id="@+id/delete_superhero"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:src="@drawable/ic_delete" />

            <ImageView
                android:id="@+id/edit_superhero"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:src="@drawable/ic_edit" />


        </LinearLayout>

    </androidx.constraintlayout.widget.ConstraintLayout>
</androidx.cardview.widget.CardView>
```

## Implementando Adapter
Nuestro adapter se usará para decir al recyclerview qué elementos tiene que pintar dentro. Para crear una clase de este estilo tendremos que heredar de *RecyclerView.Adapter<VH extends ViewHolder>*, clase parametrizada que recibirá nuestro *ViewHolder*. Al extender de esta clase, el compilador nos dirá que tenemos que implementar 3 métodos:

- *onCreateViewHolder()*: Devuelve la celda correspondiente a nuestro *ViewHolder*
- *onBindViewHolder()*: Llena de información nuestra celda
- *getItemCount()*: Informa a la lista cuántos elementos va a tener

```kotlin
package com.iigr.recyclerviewexample.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iigr.recyclerviewexample.R
import com.iigr.recyclerviewexample.model.SuperHero

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
class SuperheroAdapter(
    private val superHeroList: List<SuperHero>,
    private val onSuperHeroClick: (SuperHero) -> Unit,
    private val onDeleteSuperHero: (Int) -> Unit,
    private val onEditSuperHero: (Int) -> Unit,
) : RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(
            layoutInflater.inflate(
                R.layout.view_holder_superhero,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item, onSuperHeroClick, onDeleteSuperHero, onEditSuperHero)
    }

    override fun getItemCount(): Int = superHeroList.size
}
```

En nuestro caso, el constructor de nuestro *SuperheroAdapter* recibirá 4 argumentos:

- Un listado con los datos que va a contener la lista
- 3 funciones lambda para implementar diversas acciones con cada celda, que veremos más adelante

Si nos fijamos, el método *getItemCount()* devuelve el tamaño de la lista que recibe el adapter, ya que esa será la cantidad de elementos que va a contener el *recyclerview*

## Implementando ViewHolder
Nuestro viewholder se usará para indicar al recyclerview qué tiene que pintar en cada celda y cómo lo tiene que pintar. Heredará de *RecyclerView.ViewHolder*

```kotlin
package com.iigr.recyclerviewexample.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iigr.recyclerviewexample.databinding.ViewHolderSuperheroBinding
import com.iigr.recyclerviewexample.model.SuperHero
import com.iigr.recyclerviewexample.model.publisherColor

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
class SuperHeroViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ViewHolderSuperheroBinding.bind(view)

    fun render(
        superHero: SuperHero,
        onSuperHeroClick: (SuperHero) -> Unit,
        onDeleteSuperHero: (Int) -> Unit,
        onEditSuperHero: (Int) -> Unit
    ) {
        with(binding) {
            superheroContainer.setOnClickListener { onSuperHeroClick(superHero) }
            deleteSuperhero.setOnClickListener { onDeleteSuperHero(adapterPosition) }
            editSuperhero.setOnClickListener { onEditSuperHero(adapterPosition) }

            name.text = superHero.name
            realName.text = superHero.realName
            publisher.text = superHero.publisher.publisherName
            publisher.setTextColor(superHero.publisher.publisherColor())
            avatar.setImageResource(superHero.photo)
        }
    }

}
```

La función *render(...)* es la que hemos visto que se llama en el método *onBindViewHolder(...)* de nuestro adapter

Con esto ya tendríamos implementado todo lo refente a nuestra lista. Faltaría configurar nuestro activity para que funcione todo.

## Implementación del Activity

### Fuente de datos
Crearemos una clase llamada *SuperHeroProvider* que tendrá una lista hardcodeada con distintos objetos de este tipo para poder llenar de información el *recyclerview*. Estos datos normalmente vendrían de consumir algún servicio externo a través de *Retrofit* o similar.

```kotlin
package com.iigr.recyclerviewexample.data

import com.iigr.recyclerviewexample.R
import com.iigr.recyclerviewexample.model.Publisher
import com.iigr.recyclerviewexample.model.SuperHero

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
object SuperHeroProvider {

    fun getSuperHeroes(): List<SuperHero> {
        return listOf(
            SuperHero("Spiderman", "Peter Parker", Publisher.Marvel, R.drawable.spiderman),
            SuperHero("Wolverine", "James Howlett", Publisher.Marvel, R.drawable.logan),
            SuperHero("Batman", "Bruce Wayne", Publisher.Dc, R.drawable.batman),
            SuperHero("Thor", "Thor Odinson", Publisher.Marvel, R.drawable.thor),
            SuperHero("Flash", "Barry Allen", Publisher.Dc, R.drawable.flash),
            SuperHero("Green Lantern", "Hal Jordan", Publisher.Dc, R.drawable.green_lantern),
            SuperHero("Wonder Woman", "Princess Diana", Publisher.Dc, R.drawable.wonder_woman),
            SuperHero("Spawn", "Al Simmons", Publisher.Image, R.drawable.spawn)
        )
    }

}
```
Los recursos drawable están dentro del carpeta del proyecto en el directorio drawable.

### Código del Activity

```kotlin
package com.iigr.recyclerviewexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iigr.recyclerviewexample.R
import com.iigr.recyclerviewexample.data.SuperHeroProvider
import com.iigr.recyclerviewexample.databinding.ActivityMainBinding
import com.iigr.recyclerviewexample.model.Publisher
import com.iigr.recyclerviewexample.model.SuperHero
import com.iigr.recyclerviewexample.ui.recyclerview.SuperheroAdapter

/**
 * @author iigr
 * Created by Ivan González Rincón from GMV on 20/09/2022
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superheroMutableList: MutableList<SuperHero> =
        SuperHeroProvider.getSuperHeroes().toMutableList()
    private lateinit var superheroAdapter: SuperheroAdapter

    // To build an horizontal list, the second param should be LinearLayoutManager.HORIZONTAL
    private val linearLayoutManager =
        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        setupButton()
    }

    private fun setupButton() {
        with(binding) {
            addSuperhero.setOnClickListener {
                createSuperhero()
            }
        }

    }

    private fun createSuperhero() {
        val newSuperhero = SuperHero(
            name = "Murder Falcon",
            realName = "??????",
            publisher = Publisher.Image,
            photo = R.drawable.murf
        )

        superheroMutableList.add(newSuperhero)
        superheroAdapter.notifyItemInserted(superheroMutableList.size - 1)
        linearLayoutManager.scrollToPositionWithOffset(superheroMutableList.size - 1, 20)
    }

    private fun setupRecyclerView() {
        // Used to add divider item between list items
        val decoration = DividerItemDecoration(this, linearLayoutManager.orientation)

        superheroAdapter = SuperheroAdapter(
            superHeroList = superheroMutableList,
            onSuperHeroClick = { onSuperHeroClickHandler(it) },
            onDeleteSuperHero = { onDeleteSuperHeroHandler(it) },
            onEditSuperHero = { onEditSuperHeroHandler(it) }
        )

        with(binding) {
            with(superheroList) {
                layoutManager = linearLayoutManager
                adapter = superheroAdapter
                addItemDecoration(decoration)
            }
        }
    }

    private fun onSuperHeroClickHandler(superHero: SuperHero) {
        Toast.makeText(this@MainActivity, superHero.name, Toast.LENGTH_SHORT).show()
    }

    private fun onDeleteSuperHeroHandler(position: Int) {
        superheroMutableList.removeAt(position)
        superheroAdapter.notifyItemRemoved(position)
    }

    private fun onEditSuperHeroHandler(position: Int) {
        val editedSuperhero = superheroMutableList[position]
        editedSuperhero.name = "New name"
        superheroAdapter.notifyItemChanged(position)
    }

}
```