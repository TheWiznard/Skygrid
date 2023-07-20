package com.possible_triangle.skygrid.api.xml

import nl.adaptivity.xmlutil.serialization.XmlSerialException

class DeserializationException(
    val location: String?,
    val field: String,
    val candidates: Collection<Any> = emptyList(),
) :
    XmlSerialException("Unknown field $field at $location")