package com.example.institutoharia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.institutoharia.ui.theme.InstitutoHariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstitutoHariaTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(0) }

    if (shouldShowOnboarding == 0) {
        MiInicio(
            fpbClick = { shouldShowOnboarding = 1 },
            fpmClick = { shouldShowOnboarding = 2 },
            fpsClick = { shouldShowOnboarding = 3 }
        )
    } else if (shouldShowOnboarding == 1){
        MiFPB(
            inicioClick = { shouldShowOnboarding = 0 },
            fpmClick = { shouldShowOnboarding = 2 },
            fpsClick = { shouldShowOnboarding = 3 }
        )
    } else if (shouldShowOnboarding == 2) {
        MiFPM(
            fpbClick = { shouldShowOnboarding = 1},
            inicioClick = { shouldShowOnboarding = 0},
            fpsClick = {shouldShowOnboarding = 3}
        )
    } else {
        MiFPS(
            fpbClick = { shouldShowOnboarding = 1},
            fpmClick = { shouldShowOnboarding = 2},
            inicioClick = {shouldShowOnboarding = 0}
        )
    }
}

/******************* PRIMERA PÁGINA **********************/
@Composable
fun MiInicio(fpbClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            MiTopBar()
            Image(
                painter = painterResource(id = R.drawable.centro_menu_1),
                contentDescription = "centro_menu_1.png"
            )
            MiInformacionDelCentro()
            MisBotonesInicio(fpbClick, fpmClick, fpsClick)
            MiProyectos()
        }
    }
}

@Composable
fun MiInformacionDelCentro() {
    MiTitulo("Información del Centro", FontWeight.Bold, TextAlign.Center)
    MiContenido("Dirección: C. Santiago Noda, 4, 35520 Haría, Las Palmas.\n" + "Contactos: 928 30 30 14\n" + "Email: 35009206@gobiernodecanarias.org.\n" + "Dispone de la ESO, Bachillerato y FP Básica, Grado Medio y Superior de Informática y Comunicaciones.\n" + "Cuenta con transporte escolar gratuito, turno de mañana y cafetería.")
}

@Composable
fun MisBotonesInicio(fpbClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit) {
    Row {
        MiBotonPlaneate(onClick = fpbClick, imagenBoton = R.drawable.fpb, contentDescription = "fpb.png")
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpm, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = fpsClick, imagenBoton = R.drawable.fps, contentDescription = "fps.png")
    }
}
/******************* PRIMERA PÁGINA **********************/


/****************** PROYECTOS ************************/
@Composable
fun MiProyectos(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.canaletafinal,
        R.drawable.htmlfinal,
        R.drawable.juegofinal  ,
        R.drawable.montajefinal,
        R.drawable.packetfinal,
        R.drawable.centrofinal
    ),
    nombre: List<String> = listOf(
        "Cable estructurado",
        "Diseño de páginas webs",
        "Elaboración de juegos",
        "Montaje de equipos",
        "Diseño de red",
        "Formación en centros de trabajos"
    ),
    descripcion: List<String> = listOf(
        "Hemos diseñado y ordenado los cables de un aula de informática y colocado unas canaletas en unas mesas para ordenar los cables.\n Formación profesional : Básica, Medio",
        "El Lenguaje de Marcado de Hipertexto (HTML) es el código que se utiliza para estructurar y desplegar una página web y sus contenidos. Por ejemplo, sus contenidos podrían ser párrafos, una lista con viñetas, o imágenes y tablas de datos.\n Formacón profesional: Medio y Superior",
        "Empezaremos a aprender a programar juegos sencillos como el juego de la serpiente, 2048, etc.\n Formación profesional: Superior\n",
        "Vamos a aprender a montar y desmontar equipos y saber cada parte de él, así como disco duro, memoria RAM, etc.\n Formación profesional: Básica, Medio y Superior",
        "Hemos creado y manipulado ordenadores, routers, switch, etc.\n Formación profesional: Basica y medio",
        "Vamos a estar 2 meses y medio trabajando en empresas para obtener experiencia laboral.\n Formación profesional: Básica, Medio, Superior"
    )

){
    MiForProyectos(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForProyectos(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Proyectos Generales", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}
/****************** PROYECTOS ***********************/


/******************* FP BASICO **********************/
@Composable
fun MiFPB(inicioClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit){
    LazyColumn (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        item{
            MiTopBar()
            MiInformacion_RequisitosFPB()
            MisBotonesFPB(inicioClick, fpmClick, fpsClick)
            MisAsiganturasFPB1()
            MisAsiganturasFPB2()
        }
    }
}

@Composable
fun MiInformacion_RequisitosFPB(){
    CadaInformacion_Requisitos(
        titulo = "Información del Ciclo",
        contenido =
            "Tiene una duración de 2000 horas totales.\n" +
            "Se aprende montaje de equipos en sistemas microinformáticos y redes de transmisión de datos, " +
            "operaciones auxiliares en montaje y mantenimiento de sistemas informáticos, " +
            "realizar operaciones para el almacenamiento y transporte de sistemas, periféricos y " +
            "consumibles; y por último realizar comprobaciones rutinarias de verificación en el montaje"
    )
    CadaInformacion_Requisitos(
        titulo = "Requisitos del Ciclo",
        contenido =
            "Para poder acceder a la FP Básica, es necesario tener quince años o cumplirlos durante el año natural y no superar los diecisiete en el momento de acceso.\n" +
            "Haber cursado el primer ciclo de Educación Secundaria Obligatoria (3ºESO) o haber cursado el segundo ciclo de Educación Secundaria Obligatoria.\n" +
            "Haber propuesto el equipo docente a los padres, madres o tutores legales la incorporación del alumno o alumna a un ciclo de Formación Profesional Básica.\n" +
            "Se debe cumplir con todos los requisitos."
    )
}

@Composable
fun MisBotonesFPB(inicioClick: () -> Unit, fpmClick: () -> Unit, fpsClick: () -> Unit){
    Row {
        MiBotonPlaneate(onClick = inicioClick, imagenBoton = R.drawable.inicio, contentDescription = "inicio.png")
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpm, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = fpsClick, imagenBoton = R.drawable.fps, contentDescription = "fps.png")
    }
}

@Composable
fun MisAsiganturasFPB1(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.cienciasaplicadas,
        R.drawable.comunicacionsociedad,
        R.drawable.equiposelectricos,
        R.drawable.mantenimiento,
    ),
    nombre: List<String> = listOf(
        "Ciencias Aplicadas I",
        "Comunicación y sociedad I",
        "Equipos Eléctricos y Electrónicos",
        "Instalación y mantenimiento para transmisión de datos"
    ),
    descripcion: List<String> = listOf(
        "Aplicación del conocimiento científico básico a necesidades humanas y al desarrollo tecnológico.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas.",
        "Se desarrolla las habilidades y destrezas lingüísticas, además de alcanzar el nivel de precisión, claridad y fluidez requeridas, para comunicarse en su entorno.\nHoras Semanales: 6 horas\nHoras Totales: 215 horas",
        "Se basa en el montaje y mantenimiento de equipos eléctricos y electrónicos\nHoras Semanales: 11 horas\nHoras Totales: 305 horas",
        "Se administra, gestiona, instala y mantiene las redes.\nHoras Semanales: 7 horas\nHoras Totales: 217 horas"
    )

){
    MiForAsignaturasFPB1(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForAsignaturasFPB1(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Asignaturas del 1.ᵉʳ año", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}

@Composable
fun MisAsiganturasFPB2(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.montaje,
        R.drawable.explotacion,
        R.drawable.fct  ,
        R.drawable.comunicacionsociedad,
        R.drawable.cienciasaplicadas
    ),
    nombre: List<String> = listOf(
        "Montaje y mantenimiento de sistemas y componentes informáticos",
        "Operaciones auxiliares para la configuración y la explotación",
        "Formación en centros de trabajo",
        "Comunicaciones y sociedad II",
        "Ciencias aplicadas II"
    ),
    descripcion: List<String> = listOf(
        "Se basa en el montaje y mantenimiento de sistemas y componentes informáticos.\n Horas Semanales: 9 horas\n Horas Totales: 288 horas\n",
        "Se realizan operaciones de ensamblado, operaciones de conexionado y operaciones auxiliares en el montaje y mantenimiento de equipos eléctricos y electrónicos.\n Horas Semanales: 9 horas\n Horas Totales: 250 horas",
        "Módulo donde los alumnos finalizan sus estudios en las empresas.\n Horas Semanales: 6 horas\n Horas Totales: 400 horas",
        "Se desarrolla las habilidades y destrezas lingüísticas, además de alcanzar el nivel de precisión, claridad y fluidez requeridas, para comunicarse en su entorno.\n Horas Semanales: 6 horas\n Horas Totales: 215 horas",
        "Aplicación del conocimiento científico básico a necesidades humanas y al desarrollo tecnológico.\n Horas Semanales: 6 horas\n Horas Totales: 210 horas"
    )

){
    MiForAsignaturasFPB2(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForAsignaturasFPB2(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Asignaturas del 2.º año", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}

/******************* FP BASICO **********************/


/******************* FP MEDIO **********************/
@Composable
fun MiFPM(fpbClick: () -> Unit, inicioClick: () -> Unit, fpsClick: () -> Unit){
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            MiTopBar()
            MiInformacion_RequisitosFPM()
            MisBotonesFPM(fpbClick, inicioClick, fpsClick)
            MisAsiganturasFPM1()
            MisAsiganturasFPM2()
        }
    }
}

@Composable
fun MiInformacion_RequisitosFPM(){
    CadaInformacion_Requisitos(
        titulo = "Información del Ciclo",
        contenido =
            "Tiene una duración de 2000 horas totales.\n" +
            "Se aprende a instalar y configurar software básico y de aplicación, " +
            "redes locales cableadas, inalámbricas o mixtas y conectadas a redes públicas, " +
            "a mantener servicios multiusuarios, aplicaciones y dispositivos compartidos " +
            "en un entorno de red local, montar y configurar ordenadores, etc."
    )
    CadaInformacion_Requisitos(
        titulo = "Requisitos del Ciclo",
        contenido =
            "Para acceder al Grado Medio se debe cumplir con algunos de los siguientes requisitos:\n" +
            "Tener el título de Educación Secundaria Obligatoria o nivel académico superior.\n" +
            "Tener el título de Formación Profesional Básico.\n" +
            "Título de Técnico/a o de Técnico/a Auxiliar o equivalente a efectos académicos.\n" +
            "\n" +
            "Y haber superado alguno de estas pruebas de acceso:\n" +
            "2º curso del Bachillerato Unificado y Polivalente (BUP).\n" +
            "Prueba de acceso a ciclos formativos de grado medio (se requerirá tener, al menos, diecisiete años, cumplidos en el año de realización de la prueba).\n" +
            "Prueba de acceso a la Universidad para mayores de 25 años (la superación de las pruebas de acceso a la Universidad para mayores de 40 y 45 años no es un requisito válido para acceder a FP)."
    )
}

@Composable
fun MisBotonesFPM(fpmClick: () -> Unit, inicioClick: () -> Unit, fpsClick: () -> Unit){
    Row {
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpb, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = inicioClick, imagenBoton = R.drawable.inicio, contentDescription = "inicio.png")
        MiBotonPlaneate(onClick = fpsClick, imagenBoton = R.drawable.fps, contentDescription = "fps.png")
    }
}

@Composable
fun MisAsiganturasFPM1(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.redeslocales,
        R.drawable.som,
        R.drawable.aif,
        R.drawable.montaje,
        R.drawable.fol
    ),
    nombre: List<String> = listOf(
        "Redes Locales",
        "Sistemas Operativos Monopuestos",
        "Aplicaciones Ofimáticas",
        "Montaje y mantenimiento de equipos",
        "Formación y Orientación Laboral"
    ),
    descripcion: List<String> = listOf(
        "Esta asignatura trata sobre la tecnología que existe para conectar diferentes equipos a través de una red local. Te adentrarás en el funcionamiento de todas ellas y sabrás cuáles son las más utilizadas en las empresas por el momento y qué tecnologías de red local se usarán en un futuro próximo.\n Horas Semanales: 7 horas\n",
        "Como el nombre indica, el módulo trata sobre los sistemas operativos y su configuración, todo esto se realizará creando máquinas virtuales.\n Horas Semanales: 6 horas",
        "En este módulo aprenderás a manejar a nivel de experto todas las aplicaciones ofimáticas: Word, Excel, Power Point y Acces. ¡No habrá ninguna que se te resista! Se trata de herramientas esenciales que exigen en prácticamente cualquier trabajo de oficina en la actualidad y además son la base para interiorizar con facilidad todo lo que vendrá después.\n Horas Semanales: 7 horas",
        "Gracias a este módulo conocerás cada pieza que forman los equipos microinformáticos de hoy en día. Su montaje y mantenimiento serán pan comido para ti cuando estudies la asignatura y cojas un poco de práctica.\n Horas Semanales: 7 horas",
        "Este es un módulo troncal a todos los ciclos de FP. En él te daremos las pautas para encontrar trabajo en cuanto termines los estudios: cómo hacer un curriculum, dónde dirigirte y cómo prepararte una entrevista de trabajo para convencer a la empresa de que eres el candidato ideal.\n Horas Semanales: 3 horas"
    )

){
    MiForAsignaturasFPM1(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForAsignaturasFPM1(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Asignaturas del 1.ᵉʳ año", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}

@Composable
fun MisAsiganturasFPM2(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.servicioenred,
        R.drawable.seguridadinformatica,
        R.drawable.sistemasoperativosred,
        R.drawable.empresas,
        R.drawable.aplicacionesweb,
        R.drawable.fct2,
        R.drawable.integracion
    ),
    nombre: List<String> = listOf(
        "Servicio en red",
        "Seguridad informatica",
        "Sistemas Operativos en Red",
        "Empresas",
        "Aplicaciones Web",
        "Formación en centros de trabajo",
        "Integracion"
    ),
    descripcion: List<String> = listOf(
        "Esta asignatura trata sobre la tecnología que existe para conectar diferentes equipos a través de una red local. Te adentrarás en el funcionamiento de todas ellas y sabrás cuáles son las más utilizadas en las empresas por el momento y qué tecnologías de red local se usarán en un futuro próximo.\n Horas Semanales: 7 horas",
        "Como el nombre indica, el módulo trata sobre los sistemas operativos y su configuración, todo esto se realizará creando máquinas virtuales.\n Horas Semanales: 6 horas",
        "En este módulo aprenderás a manejar a nivel de experto todas las aplicaciones ofimáticas: Word, Excel, Power Point y Acces. ¡No habrá ninguna que se te resista! Se trata de herramientas esenciales que exigen en prácticamente cualquier trabajo de oficina en la actualidad y además son la base para interiorizar con facilidad todo lo que vendrá después.\n Horas Semanales: 7 horas",
        "Gracias a este módulo conocerás cada pieza que forman los equipos microinformáticos de hoy en día. Su montaje y mantenimiento serán pan comido para ti cuando estudies la asignatura y cojas un poco de práctica.\n Horas Semanales: 7 horas",
        "Este es un módulo troncal a todos los ciclos de FP. En él te daremos las pautas para encontrar trabajo en cuanto termines los estudios: cómo hacer un curriculum, dónde dirigirte y cómo prepararte una entrevista de trabajo para convencer a la empresa de que eres el candidato ideal.\n Horas Semanales: 3 horas"
    )

){
    MiForAsignaturasFPM2(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForAsignaturasFPM2(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Asignaturas del 2.º año", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}
/******************* FP MEDIO **********************/


/******************* FP SUPERIOR **********************/
@Composable
fun MiFPS(fpbClick: () -> Unit, fpmClick: () -> Unit, inicioClick: () -> Unit){
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            MiTopBar()
            MiInformacion_RequisitosFPS()
            MisBotonesFPS(fpbClick, fpmClick, inicioClick)
            MisAsiganturasFPS1()
            MisAsiganturasFPS2()
        }
    }
}

@Composable
fun MiInformacion_RequisitosFPS(){
    CadaInformacion_Requisitos(
        titulo = "Información del Ciclo",
        contenido =
            "Tiene una duración total de 2000 horas totales.\n" +
            "Se aprende gestión de base de datos, gestión de entornos de desarrollo, " +
            "desarrollo de aplicaciones multiplataforma, configurar sistemas informáticos" +
            " y aplicar técnicas y procedimientos relacionados con la seguridad en sistemas, " +
            "servicios y aplicaciones."
    )
    CadaInformacion_Requisitos(
        titulo = "Requisitos del Ciclo",
        contenido =
            "Para acceder al Grado Superior de Desarrollo de Aplicaciones Multiplataformas se debe cumplir algunos de los siguientes requisitos:\n" +
            "Bachiller.\n" +
            "Técnico Superior de Formación Profesional o grado universitario\n" +
            "Técnico de Grado Medio de Formación Profesional o el título de Técnico o Técnica de Artes Plásticas y Diseño\n" +
            "\nY haber superado:\n" +
            "Una oferta formativa de Grado C incluida en el ciclo formativo.\n" +
            "Un curso de formación específico preparatorio y gratuito para el acceso a ciclos de grado superior en centros expresamente autorizados por la Administración educativa.\n" +
            "Una prueba de acceso."
    )
}

@Composable
fun MisBotonesFPS(fpbClick: () -> Unit, fpmClick: () -> Unit, inicioClick: () -> Unit){
    Row {
        MiBotonPlaneate(onClick = fpbClick, imagenBoton = R.drawable.fpb, contentDescription = "fpm.png")
        MiBotonPlaneate(onClick = fpmClick, imagenBoton = R.drawable.fpm, contentDescription = "fps.png")
        MiBotonPlaneate(onClick = inicioClick, imagenBoton = R.drawable.inicio, contentDescription = "inicio.png")
    }
}

@Composable
fun MisAsiganturasFPS1(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.sistemasinformaticos,
        R.drawable.basededatos,
        R.drawable.programacion,
        R.drawable.lenguajemarca,
        R.drawable.entornodesarrollo,
        R.drawable.fol2,
        R.drawable.ingles
    ),
    nombre: List<String> = listOf(
        "Sistemas informáticos",
        "Base de datos",
        "Programación",
        "Lenguaje de marcas",
        "Entorno de desarrollo",
        "Formación y orientación laboral",
        "Inglés"
    ),
    descripcion: List<String> = listOf(
        "El objetivo del módulo de \"Sistemas Informáticos\" pretende formar al alumno en los distintos sistemas informáticos, principalmente en lo que respecta a los sistemas operativos, las aplicaciones de propósito general y las redes de área local.\n Horas Semanales : 5 horas\n Horas Totales: 160 horas.\n",
        "Este módulo profesional contiene la formación necesaria para desempeñar funciones relacionadas con la gestión de la información almacenada en bases de datos y el desarrollo de aplicaciones que acceden a bases de datos.\n Horas Semanales : 6 horas\n Horas Totales: 190 horas\n",
        "Este módulo profesional contiene parte de la formación necesaria para desempeñar la función de programación de aplicaciones de propósito general en lenguajes orientados a objetos.\n Horas Semanales : 7 horas\n Horas Totales: 256 horas.\n",
        "Este módulo profesional contiene la formación necesaria para desempeñar la funciónde gestión y explotación de sistemas de información.\n Horas Semanales : 4 horas\n Horas Totales: 128 horas\n",
        "Este módulo profesional contiene la formación necesaria para la utilización de las herramientas software disponibles, la elaboración de documentación interna y técnica de la aplicación, elaboración y ejecución de pruebas y la optimización de código.\n Horas Semanales : 3 horas\n Horas Totales: 96 horas",
        "Este módulo profesional contiene la formación necesaria para que el alumno pueda insertarse laboralmente y desarrollar su carrera profesional en el sector del Desarrollo de Aplicaciones multiplataforma.\n Horas Semanales : 3 horas\n Horas Totales: 96 horas",
        "La asignatura de ingles se enfoca principalmente al sector informático, enseñandote nuevo vocabulario relacionado con el mundo informático\n Horas Semanales : 2 horas\n Horas Totales: 64 horas\n"
    )

){
    MiForAsignaturasFPS1(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForAsignaturasFPS1(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Asignaturas del 1.ᵉʳ año", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}

@Composable
fun MisAsiganturasFPS2(
    imagenAsignatura: List<Int> = listOf(
        R.drawable.accesodatos,
        R.drawable.desarrollointerfaces,
        R.drawable.programacionmultimedia,
        R.drawable.programacionserviciosyprocesos,
        R.drawable.gestionempresarial,
        R.drawable.desarrolloaplicaciones,
        R.drawable.fct3,
        R.drawable.empresainiciativa
    ),
    nombre: List<String> = listOf(
        "Acceso a Datos",
        "Desarrollo de Interfaces",
        "Programación multimedia",
        "Programcación de servicios y procesos",
        "Gestión empresarial",
        "Desarrollo de Aplicaciones Multiplataforma",
        "Formación en centros de trabajos",
        "Empresa e iniciativa emprendedora"
    ),
    descripcion: List<String> = listOf(
        "El objetivo de la asignatura de Acceso a Datos es proporcionar a los estudiantes los conocimientos y habilidades necesarios para diseñar, implementar y gestionar sistemas de bases de datos de manera efectiva y segura.\n Horas Semanales : 7 horas\n Horas Totales: 147 horas\n",
        "El objetivo de la asignatura de Desarrollo de Interfaces en el ciclo superior de DAM es proporcionar a los estudiantes los conocimientos y habilidades necesarios para diseñar y desarrollar interfaces de usuario para aplicaciones informáticas.\n Horas Semanales : 7 horas\n Horas Totales: 147 horas",
        "El objetivo de la asignatura de Programación multimedia y dispositivos móviles en el ciclo superior de DAM es proporcionar a los estudiantes los conocimientos y habilidades necesarios para desarrollar aplicaciones multimedia y para dispositivos móviles.\n Horas Semanales : 4 horas\n Horas Totales: 84 horas",
        "El objetivo de la asignatura de Programación de servicios y procesos en el ciclo superior de DAM es proporcionar a los estudiantes los conocimientos y habilidades necesarios para diseñar, implementar y desplegar servicios y procesos en sistemas informáticos.\n Horas Semanales : 4 horas\n Horas Totales: 84 horas",
        "La asignatura de Sistemas de gestión empresarial en el ciclo superior de DAM tiene como objetivo proporcionar a los estudiantes los conocimientos necesarios para comprender el funcionamiento de los sistemas de gestión empresarial y aprender a utilizar herramientas para su implementación.\n Horas Semanales : 3 horas\n Horas Totales: 63 horas",
        "La asignatura de Proyecto de desarrollo de aplicaciones multiplataforma en el ciclo superior de DAM tiene como objetivo proporcionar a los estudiantes la oportunidad de aplicar los conocimientos y habilidades adquiridos en las diferentes asignaturas del ciclo formativo en un proyecto de desarrollo de software real, con el fin de que los estudiantes puedan desarrollar habilidades en la gestión y dirección de proyectos de software.\n Horas Semanales : 3 horas\n Horas Totales: 64 horas",
        "En el caso del ciclo de DAM, durante la FCT, los estudiantes pueden trabajar en empresas relacionadas con el desarrollo de software y aplicaciones para diferentes plataformas, como Android, iOS o Windows. Los estudiantes pueden participar en proyectos de desarrollo de software, pruebas de calidad, mantenimiento de sistemas, entre otras actividades relacionadas con el desarrollo de aplicaciones multiplataforma.\n Horas Semanales : Dependiendo de la empresa\n Horas Totales: 346 horas",
        "En el caso del ciclo de DAM, durante la FCT, los estudiantes pueden trabajar en empresas relacionadas con el desarrollo de software y aplicaciones para diferentes plataformas, como Android, iOS o Windows. Los estudiantes pueden participar en proyectos de desarrollo de software, pruebas de calidad, mantenimiento de sistemas, entre otras actividades relacionadas con el desarrollo de aplicaciones multiplataforma.\n Horas Semanales : 3\n Horas Totales: 63 horas"
    )

){
    MiForAsignaturasFPS2(imagenAsignatura, nombre, descripcion)
}

@Composable
fun MiForAsignaturasFPS2(imagenAsignatura: List<Int>, nombre: List<String>, descripcion: List<String>) {

    var expandirBox by remember {mutableStateOf(-1)}

    MiTitulo(texto = "Asignaturas del 2.º año", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(imagenAsignatura.size) { cadaItem ->
            CadaAsignatura_Proyectos(
                imagen = imagenAsignatura[cadaItem],
                nombre = nombre[cadaItem],
                descripcion = descripcion[cadaItem],
                expandir = expandirBox == cadaItem
            ) {
                expandirBox = if (expandirBox == cadaItem) -1 else cadaItem
            }
        }
    }
}
/******************* FP SUPERIOR **********************/


/******************* GENERAL **********************/
@Composable
fun MiTopBar() {
    Row(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(75.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www3.gobiernodecanarias.org/medusa/edublog/iesharia/")) }

        TextButton(onClick = { context.startActivity(intent) }) {
            Image(
                painter = painterResource(id = R.drawable.logo_ies_haria),
                contentDescription = "logoPlaneate.png"
            )
        }

        Spacer(modifier = Modifier.padding(6.dp))

        Text(
            text = "I.E.S Haría",
            color = Color.Green
        )
    }
}

@Composable
fun MiBotonPlaneate(onClick: () -> Unit, @DrawableRes imagenBoton: Int, contentDescription: String) {
    TextButton(
        onClick = { onClick() },
        modifier = Modifier.size(125.dp)
    ) {
        Image(
            painter = painterResource(id = imagenBoton),
            contentDescription = contentDescription,
            modifier = Modifier.size(125.dp)
        )
    }
}

@Composable
fun MiTitulo(texto: String, fontWeight: FontWeight, textAlign: TextAlign) {
    Text(text = texto, fontSize = 24.sp, fontWeight = fontWeight, textAlign = textAlign, modifier = Modifier.padding(16.dp))
}

@Composable
fun MiContenido(texto: String, ) {
    Text(text = texto, fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp, end = 4.dp))
    Spacer(modifier = Modifier.padding(6.dp))
}

@Composable
fun CadaInformacion_Requisitos(
    titulo: String,
    contenido: String
){
    OutlinedCard(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.padding(12.dp)
    ) {
        MiTitulo(texto = titulo, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        MiContenido(texto = contenido)
    }
}

@Composable
fun CadaAsignatura_Proyectos(
    imagen: Int,
    nombre: String,
    descripcion: String,
    expandir: Boolean,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(320.dp)
            .clickable {
                onItemClick()
            }
            .border(2.dp, Color.Black)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = nombre
            )

            if (expandir) {
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = descripcion, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }
}
/******************* GENERAL **********************/