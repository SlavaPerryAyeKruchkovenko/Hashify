package org.gigachad.data.param

import org.gigachad.data.Algorithm

class AlgorithmParam: Param<Algorithm>() {

  override val alias: List<Algorithm> = Algorithm.entries.toList()
}
