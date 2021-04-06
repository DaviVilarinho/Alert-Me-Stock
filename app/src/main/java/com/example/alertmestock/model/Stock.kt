data class Stock(val tick:String,
                 var last_price: Double,
                 var target_price: Double,
                 var isAsc:Boolean) {
    val reached = check()

    fun isReached():Boolean {
        if (isAsc) {
            if (last_price >= target_price) return true
            else return false
        } else {
            if (last_price <= target_price) return true
            else return false
        }
    }

    private fun check():String {
        if (isReached()) return "✅"
        else return "⛔"
    }
}