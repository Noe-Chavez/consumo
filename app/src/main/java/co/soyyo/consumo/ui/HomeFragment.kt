package co.soyyo.consumo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.soyyo.consumo.R
import co.soyyo.consumo.databinding.FragmentHomeBinding
import co.soyyo.consumo.entities.ImageEntity
import co.soyyo.consumo.ui.adapter.ImageAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var fragmentComponents: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentComponents = FragmentHomeBinding.bind(view)

        val listTest = ArrayList<ImageEntity>(5)
        listTest.add(ImageEntity("The Galactic Center in Radio from MeerKAT", "2022-02-02", "Free", "https://apod.nasa.gov/apod/image/2202/MwCenter_MeerKATMunoz_1080.jpg", "What's happening at the center of our galaxy? It's hard to tell with optical telescopes since visible light is blocked by intervening interstellar dust. In other bands of light, though, such as radio, the galactic center can be imaged and shows itself to be quite an interesting and active place.  The featured picture shows the latest image of our Milky Way's center by the MeerKAT array of 64 radio dishes in South Africa. Spanning four times the angular size of the Moon (2 degrees), the image is impressively vast, deep, and detailed.  Many known sources are shown in clear detail, including many with a prefix of Sgr, since the galactic center is in the direction of the constellation Sagittarius.  In our Galaxy's Center lies Sgr A, found here in the image center, which houses the Milky Way's central supermassive black hole.  Other sources in the image are not as well understood, including the Arc, just to the left of Sgr A, and numerous filamentary threads. Goals for MeerKAT include searching for radio emission from neutral hydrogen emitted in a much younger universe and brief but distant radio flashes.   Open Science: Browse 2,700+ codes in the Astrophysics Source Code Library"))
        listTest.add(ImageEntity("Embraced by Sunlight", "2022-02-03", "Juan Luis Cánovas Pérez", "https://apod.nasa.gov/apod/image/2202/VenusMoonSamePhase1024.jpg", "ven though Venus (left) was the brightest planet in the sky it was less than 1/30th the apparent size of the Moon on January 29. But as both rose before the Sun they shared a crescent phase, and for a moment their visible disks were each about 12 percent illuminated as they stood above the southeastern horizon. The similar sunlit crescents were captured in these two separate images. Made at different magnifications, each panel is a composite of stacked video frames taken with a small telescope. Venus goes through a range of phases like the Moon as the inner planet wanders from evening sky to morning sky and back again with a period of 584 days. Of course the Moon completes its own cycle of phases, a full lunation, in about 29.5 days."))
        listTest.add(ImageEntity("Moons at Twilight", "2022-02-04", "Robert Fedez", "https://apod.nasa.gov/apod/image/2202/IMG_1869Fedez1024.png", "ven though Jupiter was the only planet visible in the evening sky on February 2, it shared the twilight above the western horizon with the Solar System's brightest moons. In a single exposure made just after sunset, the Solar System's ruling gas giant is at the upper right in this telephoto field-of-view from Cancun, Mexico. The snapshot also captures our fair planet's own natural satellite in its young crescent phase. The Moon's disk looms large, its familiar face illuminated mostly by earthshine. But the four points of light lined-up with Jupiter are Jupiter's own large Galilean moons. Top to bottom are Ganymede, [Jupiter], Io, Europa, and Callisto. Ganymede, Io, and Callisto are physically larger than Earth's Moon while water world Europa is only slightly smaller."))

        fragmentComponents.recyclerViewImages.adapter = ImageAdapter(listTest)

    }

}