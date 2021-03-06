/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/**
 * $Id: $
 */
package com.linkedin.restli.examples.greetings.server;

import java.util.HashMap;
import java.util.Map;

import com.linkedin.restli.common.ComplexResourceKey;
import com.linkedin.restli.examples.StringTestKeys;
import com.linkedin.restli.examples.greetings.api.Message;
import com.linkedin.restli.examples.greetings.api.Tone;
import com.linkedin.restli.examples.greetings.api.TwoPartKey;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.ComplexKeyResourceTemplate;

/**
 * Demonstrates a resource with a complex key.
 * @author jbetz
 *
 */
@RestLiCollection(
  name = "complexKeys",
  namespace = "com.linkedin.restli.examples.greetings.client",
  keyName="keys"
  )
public class ComplexKeysResource extends ComplexKeyResourceTemplate<TwoPartKey, TwoPartKey, Message>
{
  private Map<String, Message> _db = new HashMap<String, Message>();

  public ComplexKeysResource()
  {
    addExample(StringTestKeys.URL, StringTestKeys.URL2, StringTestKeys.URL + " " + StringTestKeys.URL2);
    addExample(StringTestKeys.SIMPLEKEY, StringTestKeys.SIMPLEKEY2, StringTestKeys.SIMPLEKEY + " " + StringTestKeys.SIMPLEKEY2);
  }

  private void addExample(String majorKey, String minorKey, String text)
  {
    TwoPartKey key = new TwoPartKey();
    key.setMajor(majorKey);
    key.setMinor(minorKey);
    Message message = new Message();
    message.setId(keyToString(key));
    message.setMessage(text);
    message.setTone(Tone.SINCERE);
    _db.put(keyToString(key), message);
  }

  @Override
  public Message get(ComplexResourceKey<TwoPartKey, TwoPartKey> complexKey)
  {
    TwoPartKey key = complexKey.getKey();
    return _db.get(keyToString(key));
  }

  private String keyToString(TwoPartKey key)
  {
    return key.getMajor() + " " + key.getMinor();
  }
}
