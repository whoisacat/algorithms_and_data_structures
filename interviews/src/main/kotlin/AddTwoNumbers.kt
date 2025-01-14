/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

fun main() {
    val solution = Solution()
    val l1 = ListNode.quickList(listOf(2,4,3))
    val l2 = ListNode.quickList(listOf(5,6,4))
    println(solution.addTwoNumbers(l1, l2))
}

class Solution {
    fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode? {

        var first:ListNode? = l1
        var second:ListNode? = l2
        val dummy = ListNode(-1)
        dummy.next = ListNode(((first?.`val` ?: 0) + (second?.`val` ?: 0)) % 10)
        var next = dummy.next!!
        var over = ((first?.`val` ?: 0) + (second?.`val` ?: 0) > 9)
        while (first?.next != null || second?.next != null || over) {
            val value = ((first?.next?.`val` ?: 0) + (second?.next?.`val` ?: 0) + if (over) 1 else 0)
            next.next = ListNode(value % 10)
            next = next.next!!
            over = value > 9
            first = first?.next
            second = second?.next
        }
        return dummy.next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null


    companion object {
        fun quickList(nodes: List<Int>): ListNode {
            val dummy = ListNode(-1)
            nodes.reversed().forEach {
                val temp = ListNode(it)
                temp.next = dummy.next
                dummy.next = temp
            }
            return dummy.next!!
        }
    }
}
