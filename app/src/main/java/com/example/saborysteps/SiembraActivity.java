package com.example.saborysteps;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SiembraActivity extends AppCompatActivity {

    private RecyclerView plantingTechniquesRecyclerView;
    private RecyclerView animalCareRecyclerView;
    private TechniqueAdapter plantingAdapter;
    private TechniqueAdapter animalCareAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siembra);

        plantingTechniquesRecyclerView = findViewById(R.id.plantingTechniquesRecyclerView);
        animalCareRecyclerView = findViewById(R.id.animalCareRecyclerView);

        // Set up RecyclerViews
        plantingTechniquesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        animalCareRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for testing
        List<Technique> plantingTechniques = new ArrayList<>();
        plantingTechniques.add(new Technique("Técnica de Siembra Directa", "Descripción de la técnica de siembra directa.", "https://www.reddearboles.org/noticias/nwarticle/699/3/que-siembra-y-cuales-su-importancia"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Línea", "Descripción de la técnica de siembra en línea.", "https://www.reddearboles.org/noticias/nwarticle/699/3/que-siembra-y-cuales-su-importancia"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Surcos", "Descripción de la técnica de siembra en surcos.", "https://www.intagri.com/articulos/cereales/fundamentos-del-sistema-de-siembra-en-surco-angosto-en-el-cultivo-del-maiz"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Almácigos", "Descripción de la técnica de siembra en almácigos.", "https://www.fecoagro.com.ar/que-es-la-siembra-en-almacigo/"));
        plantingTechniques.add(new Technique("Técnica de Siembra a Mano", "Descripción de la técnica de siembra a mano.", "https://www.youtube.com/watch?v=6rpytQlqFSw"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Montículos", "Descripción de la técnica de siembra en montículos.", "https://sitioagropecuario.com/cultivo-de-monticulos-por-que-y-como/"));
        plantingTechniques.add(new Technique("Técnica de Siembra a Chorrillo", "Descripción de la técnica de siembra a chorrillo.", "https://rotoplas.com.ar/agroindustria/que-es-la-siembra-a-chorrillo/#:~:text=La%20siembra%20a%20chorrillo%20es,volver%20a%20colocar%20tierra%20encima."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Hileras", "Descripción de la técnica de siembra en hileras.", "https://www.agropinos.com/blog/caractersticas-del-cultivo-en-hileras#:~:text=¿En%20qué%20consisten%20los%20cultivos%20en%20hileras%3F&text=Populares%20en%20la%20obtención%20de,que%20puede%20dar%20la%20tierra."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Rotación de Cultivos", "Descripción de la técnica de siembra en rotación de cultivos.", "https://rodaleinstitute.org/es/por-qué-orgánico/prácticas-de-agricultura-orgánica/rotaciones-de-cultivos/#:~:text=La%20rotación%20de%20cultivos%20es,plantado%20un%20campo%20de%20maíz."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Hoyos", "Descripción de la técnica de siembra en hoyos.", "https://rotoplas.com.ar/agroindustria/por-que-variar-el-metodo-de-siembra/"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Viveros", "Descripción de la técnica de siembra en viveros.", "https://www.elsemillero.co/blog/el-blog-del-semillero-1/post/el-vivero-clave-para-una-plantacion-productiva-4"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Cajones", "Descripción de la técnica de siembra en cajones.", "https://www.clarin.com/familias/huerta-cajones-madera-paso-paso-armarla-casa_0__sdPSvNPd.html"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Terrazas", "Descripción de la técnica de siembra en terrazas.", "https://www.studysmarter.es/resumenes/geografia/geografia-economica/cultivo-en-terrazas/#:~:text=La%20agricultura%20en%20terrazas%20es%20un%20método%20de%20paisajismo%20agrícola,en%20zonas%20montañosas%20o%20accidentadas."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Bandejas", "Descripción de la técnica de siembra en bandejas.", "https://eoliagarden.com/como-germinar-una-semilla-bandejas-de-germinacion/#:~:text=Siembra%3A,de%20drenaje%20de%20cada%20celda."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Bolsas", "Descripción de la técnica de siembra en bolsas.", "https://polisantafe.com.ar/como-sembrar-en-bolsas-de-plastico/"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Camellones", "Descripción de la técnica de siembra en camellones.", "https://redagricola.com/las-ventajas-de-un-sistema-innovador-pero-poco-usado-en/"));
        plantingTechniques.add(new Technique("Técnica de Siembra Intercalada", "Descripción de la técnica de siembra intercalada.", "https://eos.com/es/blog/cultivos-intercalados/#:~:text=El%20sistema%20de%20cultivos%20intercalados,y%20las%20combinaciones%20de%20ellas."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Filas Dobles", "Descripción de la técnica de siembra en filas dobles.", "https://www.bednar.com/es/cultivo-entre-lineas/"));
        plantingTechniques.add(new Technique("Técnica de Siembra por Inyección", "Descripción de la técnica de siembra por inyección.", "https://www.medigraphic.com/cgi-bin/new/resumen.cgi?IDARTICULO=21336"));
        plantingTechniques.add(new Technique("Técnica de Siembra a Chorro", "Descripción de la técnica de siembra a chorro.", "https://rotoplas.com.ar/agroindustria/que-es-la-siembra-a-chorrillo/#:~:text=La%20siembra%20a%20chorrillo%20es,volver%20a%20colocar%20tierra%20encima."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Regadío", "Descripción de la técnica de siembra en regadío.", "https://www.iagua.es/respuestas/que-es-agricultura-regadio#:~:text=La%20agricultura%20de%20regadío%20consiste,transformando%20las%20zonas%20de%20secano."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Secano", "Descripción de la técnica de siembra en secano.", "https://www.iagua.es/respuestas/que-es-agricultura-secano#:~:text=La%20agricultura%20de%20secano%20es,absorción%20inmediata%20por%20los%20cultivos%20("));
        plantingTechniques.add(new Technique("Técnica de Siembra en Montículos", "Descripción de la técnica de siembra en montículos.", "http://lerf.eco.br/img/publicacoes/2012_1111%20Una%20innovacion%20tecnologica.pdf"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Hileras", "Descripción de la técnica de siembra en hileras.", "https://www.millerchemical.com/es/cultivos-y-soluciones/cultivos-en-hileras/"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Surcos", "Descripción de la técnica de siembra en surcos.", "https://lacalera.una.edu.ni/index.php/CALERA/article/view/506/886"));
        plantingTechniques.add(new Technique("Técnica de Siembra por Esquejes", "Descripción de la técnica de siembra por esquejes.", "https://www.admagazine.com/sustentabilidad/que-son-los-esquejes-y-como-se-multiplican-20210506-8480-articulos#:~:text=¿Cómo%20se%20realiza%20la%20multiplicación,una%20rama%20o%20un%20brote."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Almácigos", "Descripción de la técnica de siembra en almácigos.", "https://www.fecoagro.com.ar/que-es-la-siembra-en-almacigo/"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Invernadero", "Descripción de la técnica de siembra en invernadero.", "https://www.agropinos.com/blog/cultivos-en-invernadero"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Macetas", "Descripción de la técnica de siembra en macetas.", "https://www.mundodeportivo.com/uncomo/hogar/articulo/como-sembrar-en-macetas-27859.html"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Camas Elevadas", "Descripción de la técnica de siembra en camas elevadas.", "https://campe.ar/agricultura/metodo-de-siembras-en-camas-elevadas-como/#:~:text=Este%20proceso%2C%20como%20su%20nombre,los%20productores%2C%20cuidando%20sus%20cultivos."));
        plantingTechniques.add(new Technique("Técnica de Siembra en Contenedores", "Descripción de la técnica de siembra en contenedores.", "https://farmanywhere.ag/es/agricultura-en-contenedores/"));
        plantingTechniques.add(new Technique("Técnica de Siembra en Macrotúneles", "Descripción de la técnica de siembra en macrotúneles.", "https://smeapmexico.org/que-son-los-macrotuneles/"));


        List<Technique> animalCareTechniques = new ArrayList<>();
        animalCareTechniques.add(new Technique("Cuidado de Vacas", "Descripción del cuidado de vacas.", "https://www.clubganadero.com/cria-de-ganado/"));
        animalCareTechniques.add(new Technique("Cuidado de Gallinas", "Descripción del cuidado de gallinas.", "https://www.planetahuerto.es/guias/guia-sobre-la-cria-de-gallinas"));
        animalCareTechniques.add(new Technique("Cuidado de Caballos", "Descripción del cuidado de caballos.", "https://www.zooplus.es/magazine/caballos/salud-y-cuidados-equinos/los-cuidados-de-los-caballos"));
        animalCareTechniques.add(new Technique("Cuidado de Perros", "Descripción del cuidado de perros.", "https://postgradoveterinaria.com/cuidados-perro-recomendaciones-basicas/"));
        animalCareTechniques.add(new Technique("Cuidado de Gatos", "Descripción del cuidado de gatos.", "https://www.purina.es/cuidados/gatos/salud/aseo-cuidados-diarios/cuidados-gatos-saludables"));
        animalCareTechniques.add(new Technique("Cuidado de Ovejas", "Descripción del cuidado de ovejas.", "https://www.contegral.co/noticias/ovinos-caracteristicas-alimentacion-y-cuidados#:~:text=Además%20de%20la%20alimentación%2C%20los,su%20carne%2C%20lana%20y%20leche."));
        animalCareTechniques.add(new Technique("Cuidado de Cerdos", "Descripción del cuidado de cerdos.", "https://www.porcicultura.com/destacado/como-proteger-a-los-cerdos-destetados-conceptos-basicos-del-cuidado-de-los-lechones-de-destete"));
        animalCareTechniques.add(new Technique("Cuidado de Aves", "Descripción del cuidado de aves.", "https://www.patasycolasveterinario.com/cuidado-aves-caracteristicas-consejos-bienestar-casa/"));
        animalCareTechniques.add(new Technique("Cuidado de Conejos", "Descripción del cuidado de conejos.", "https://www.madrigueraweb.org/articulo/cuidados-basicos-del-conejo#:~:text=Los%20conejos%20siempre%20deben%20tener,pienso%20por%20kilo%20de%20peso."));
        animalCareTechniques.add(new Technique("Cuidado de Peces", "Descripción del cuidado de peces.", "https://www.mundodeportivo.com/uncomo/animales/articulo/como-cuidar-los-peces-correctamente-7179.html"));
        animalCareTechniques.add(new Technique("Cuidado de Reptiles", "Descripción del cuidado de reptiles.", "https://www.tiendanimal.es/articulos/anfibios-y-reptiles-como-cuidarlos-adecuadamente/"));
        animalCareTechniques.add(new Technique("Cuidado de Insectos", "Descripción del cuidado de insectos.", "https://cabanalanz.com/2023/02/28/medidas-para-proteger-y-conservar-los-insectos/#:~:text=Al%20reducir%20el%20uso%20de,los%20insectos%20en%20nuestro%20entorno."));
        animalCareTechniques.add(new Technique("Cuidado de Cabras", "Descripción del cuidado de cabras.", "https://certifiedhumanelatino.org/bienestar-de-las-cabras-nutricion-y-cuidados-basicos/"));
        animalCareTechniques.add(new Technique("Cuidado de Patos", "Descripción del cuidado de patos.", "https://www.expertoanimal.com/como-cuidar-un-pato-24371.html"));
        animalCareTechniques.add(new Technique("Cuidado de Pavos", "Descripción del cuidado de pavos.", "https://hvlucky.com/consejos-para-el-cuidado-y-crianza-de-los-pavos/"));
        animalCareTechniques.add(new Technique("Cuidado de Llamas", "Descripción del cuidado de llamas.", "https://es.everand.com/book/490325967/Crianza-de-llamas-La-guia-definitiva-para-la-conservacion-y-cuidado-de-las-llamas-incluyendo-consejos-sobre-como-criar-alpacas"));
        animalCareTechniques.add(new Technique("Cuidado de Alpacas", "Descripción del cuidado de alpacas.", "https://tukuybrand.com/es/informacion-cuidado-alpaca/"));
        animalCareTechniques.add(new Technique("Cuidado de Burros", "Descripción del cuidado de burros.", "https://www.elrefugiodelburrito.com/es/cuidados-y-consejos#:~:text=Los%20burros%20deben%20tener%20siempre,tras%20haber%20adecentado%20el%20establo."));
        animalCareTechniques.add(new Technique("Cuidado de Búfalos", "Descripción del cuidado de búfalos.", "https://www.contextoganadero.com/ganaderia-sostenible/algunos-de-los-cuidados-con-la-cria-de-bucerros-en-lecherias"));
        animalCareTechniques.add(new Technique("Cuidado de Chinchillas", "Descripción del cuidado de chinchillas.", "https://www.madrigueraweb.org/articulo/cuidados-basicos-de-las-chinchillas"));
        animalCareTechniques.add(new Technique("Cuidado de Hamsters", "Descripción del cuidado de hamsters.", "https://www.mundodeportivo.com/uncomo/animales/articulo/como-cuidar-a-un-hamster-322.html"));

        // Set adapters
        plantingAdapter = new TechniqueAdapter(this, plantingTechniques);
        animalCareAdapter = new TechniqueAdapter(this, animalCareTechniques);

        plantingTechniquesRecyclerView.setAdapter(plantingAdapter);
        animalCareRecyclerView.setAdapter(animalCareAdapter);
    }
}
