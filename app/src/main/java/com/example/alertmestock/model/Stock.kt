data class Stock(val tick:String,
                 var last_price: Double,
                 var target_price: Double,
                 var isAsc:Boolean) {
    val reached = isReached()

    private fun isReached():String {
        if (isAsc) {
            if (last_price >= target_price) return "✅"
            else return "⛔"
        } else {
            if (last_price <= target_price) return "✅"
            else return "⛔"
        }
    }
}