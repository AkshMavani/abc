package com.example.delete.pager


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.delete.R
import com.example.delete.databinding.ActivityMain9Binding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMain9Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain9Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val sliderItems: MutableList<slider> = ArrayList<slider>()
        sliderItems.add(slider(R.drawable.abc))
        sliderItems.add(slider(R.drawable.def))
        sliderItems.add(slider(R.drawable.jk))
        sliderItems.add(slider(R.drawable.kl))
        sliderItems.add(slider(R.drawable.op))

       binding.pager.setAdapter(SliderAdapter(sliderItems, binding.pager))
        binding.pager.setClipToPadding(false);
        binding.pager.setClipChildren(false);
        binding.pager.setOffscreenPageLimit(3);
        binding.pager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER)
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer( MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })
        binding.pager.setPageTransformer(compositePageTransformer);

    }

}