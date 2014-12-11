require 'HTTParty'

class Prefix

  def getCollection
    stack = HTTParty.post( "http://challenge.code2040.org/api/prefix", 
                          :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                          :headers => { 'Content-Type' => 'application/json' } )

    stack["result"]
  end

  def findStrings
    collection = getCollection
    print(collection)
    strings = collection["array"]
    pref = collection["prefix"]
    arrayOfStrings = Array.new
    putFoundStringsInArray(strings, pref, arrayOfStrings)
  end

  def putFoundStringsInArray(strings, pref, arrayOfStrings)
    strings.each do |string|
      if !string.start_with?(pref)
        arrayOfStrings << string
      end
    end
    arrayOfStrings
  end

  def validatePrefix
    foundStrings = HTTParty.post( "http://challenge.code2040.org/api/validateprefix",
                                   :body => { "token" => "8EBDqKCWgK", "array" => findStrings }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(foundStrings)
  end

  def print(someThing)
    puts someThing
  end
end

prefix = Prefix.new
prefix.validatePrefix
