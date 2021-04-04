import android.os.Parcel
import android.os.Parcelable

data class Stock(val tick:String,
                 var last_price:Float,
                 var target_price:Float)