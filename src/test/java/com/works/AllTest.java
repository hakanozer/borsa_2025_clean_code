package com.works;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.works")
//@IncludeClassNamePatterns(".*Test")
//@IncludeTags("security")
public class AllTest {
}
